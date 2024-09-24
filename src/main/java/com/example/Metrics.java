package com.example;

import java.util.Map;


enum ErrorType {
  MISSPELLING,
  MISCAPITILIZATION,
  DOUBLE_WORD
}
enum CorrectionType {
  MANUAL,
  SUGGESTION,
  DELETION,
  IGNORE,
  ADD_TO_DICTIONARY
}



public class Metrics {
    private int characters;
    private int lines;
    private int words;
    private Map<ErrorType, Integer> errorCounts;
    private Map<CorrectionType, Integer> correctionCounts;
    
    public Metrics() {
        
    }

    // Increment error count for the given errorType.
    public void incrementErrorCount(ErrorType errorType) {
        
    }

    // Increment correction count for the given correctionType.
    public void incrementCorrectionCount(CorrectionType correctionType) {
        
    }

    // Get the error count for the given errorType.
    public int getErrorCount(ErrorType errorType) {
        return 0;
        
    }

    // Get the correction count for the given correctionType.
    public int getCorrectionCount(CorrectionType correctionType) {
        return 0;
       
    }

    public void displayMetrics() {
       
    }
}


