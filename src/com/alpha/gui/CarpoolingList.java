/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.gui;

import com.alpha.Entite.Covoiturage;
import com.alpha.Service.ServiceGuide;
import com.alpha.Service.ServiceUser;
import com.codename1.capture.Capture;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Sof
 */
public class CarpoolingList extends BaseForm {
    
    public CarpoolingList(Resources res) throws IOException
    {
        ServiceGuide ser = new ServiceGuide();
        ArrayList<Covoiturage> L = ser.getListCarpooling();
              
    }
    
}
