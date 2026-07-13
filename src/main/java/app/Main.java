package app;

import data.GestorEntidades;
import model.gui.VentanaLlanquihueTour;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaLlanquihueTour().setVisible(true));
    }
        }


