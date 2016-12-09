package com.futuresailors.battleships.controller;

import javax.swing.JFrame;

public class ReloadedModeController extends SinglePlayerController {

    public ReloadedModeController(JFrame window, int gridSize) {
        super(window, gridSize);
    }

    public void startCircleMap() {
        super.startCircleMap();
    }

    public void startDreadnoughtMap() {
        super.startDreadnoughtMap();
    }

    public void startCorvetteMap() {
        super.startCorvetteMap();
    }
}