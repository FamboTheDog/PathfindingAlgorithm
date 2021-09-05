package com.company.view.toolbar;

import lombok.AllArgsConstructor;

import javax.swing.*;

@AllArgsConstructor
public class ButtonContainer extends JPanel{
    private JButton start;
    private JButton clear;

    public void showStart(){
        this.remove(clear);
        this.add(start);
    }

    public void showClear(){
        this.remove(start);
        this.add(clear);
    }
}
