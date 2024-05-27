package com.github.stephenwanjala.postfx.util;

import javafx.print.PrinterJob;
import javafx.scene.Node;

public class PrinterUtil {
    public static void print(Node node) {
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null && job.showPrintDialog(node.getScene().getWindow())) {
            boolean success = job.printPage(node);
            if (success) {
                job.endJob();
            }
        }
    }
}
