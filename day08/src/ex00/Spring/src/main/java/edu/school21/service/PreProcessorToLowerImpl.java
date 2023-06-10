package edu.school21.service;

import edu.school21.interfaces.PreProcessor;

public class PreProcessorToLowerImpl implements PreProcessor {
    @Override
    public String pretreatment(String text) {
        return text.toLowerCase();
    }
}
