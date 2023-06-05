package edu.school21.processor;

import com.google.auto.service.AutoService;
import edu.school21.annotations.HtmlForm;
import edu.school21.annotations.HtmlInput;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
@SupportedAnnotationTypes(value = {"edu.school21.annotations.HtmlForm","edu.school21.annotations.HtmlInput"})
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class HtmlProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        boolean status = false;
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(HtmlForm.class);
        if (!elements.isEmpty()) status = true;
        for (Element element : elements) {
            if (element instanceof TypeElement) {
                TypeElement typeElement = (TypeElement) element;
                HtmlForm htmlForm = typeElement.getAnnotation(HtmlForm.class);
                String fileName = htmlForm.filename();
                StringBuilder builder = new StringBuilder();
                builder.append("<form action = \"").append(htmlForm.action())
                        .append("\" method = \"").append(htmlForm.method()).append("\">\n");

                for (Element fieldElement : typeElement.getEnclosedElements()) {

                    if (fieldElement.getKind().isField() &&
                            fieldElement.getAnnotation(HtmlInput.class) != null) {
                        HtmlInput htmlInput = fieldElement.getAnnotation(HtmlInput.class);

                        builder
                                .append("\t<input type = \"")
                                .append(htmlInput.type())
                                .append("\" name = \"")
                                .append(htmlInput.name())
                                .append("\" placeholder = \"")
                                .append(htmlInput.placeholder())
                                .append("\">\n");
                    }
                }

                builder.append("\t<input type=\"submit\" value=\"Send\">\n");
                builder.append("</form>");

                try {
                    Path outputPath = Paths.get("target/classes", fileName);
                    BufferedWriter writer = Files.newBufferedWriter(outputPath);
                    writer.write(builder.toString());
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        return status;
    }
}
