package edu.school21.service;

import edu.school21.interfaces.PreProcessor;
import edu.school21.interfaces.Renderer;

public class RendererErrImpl implements Renderer {
    private final PreProcessor preProcessor;

    public RendererErrImpl(PreProcessor preProcessor) {
        this.preProcessor = preProcessor;
    }

    @Override
    public void output(String text) {
        String outputText = preProcessor.pretreatment(text);
        System.err.println(outputText);

    }
}
