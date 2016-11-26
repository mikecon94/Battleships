package com.futuresailors.battleships.controller;

import com.futuresailors.battleships.UIHelper;
import com.futuresailors.battleships.ai.AI;
import com.futuresailors.battleships.model.Grid;
import com.futuresailors.battleships.model.Ship;
import com.futuresailors.battleships.view.DifficultySelectionListener;
import com.futuresailors.battleships.view.DifficultySelectionPanel;
import com.futuresailors.battleships.view.PlayPanel;

import javax.swing.JFrame;
import javax.swing.Timer;

public class ReloadedModeController extends SinglePlayerController {

    public ReloadedModeController(JFrame window, int gridSize) {
        super(window, gridSize);
    }


}
