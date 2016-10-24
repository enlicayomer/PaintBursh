/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.omerenlicay;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

/**
 *
 * @author enlicayomer
 */
public class MyPen extends MyShape {

    ArrayList<Integer> ax = new ArrayList<Integer>();
    ArrayList<Integer> ay = new ArrayList<Integer>();

    public MyPen() {

    }

    public MyPen(int x1, int y1, int x2, int y2, Color color) {
        super(x1, y1, x2, y2, color);
        
        

    }

    @Override
    public void draw(Graphics g) {
        ax.add(getX2());
        ay.add(getY2());
        g.setColor( getColor() ); 
        for(int i = 0; i < ax.size() - 1; i++) {
			g.drawLine(ax.get(i), ay.get(i), ax.get(i+1), ay.get(i+1));
		}
    }

}
