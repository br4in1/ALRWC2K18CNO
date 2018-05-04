/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alpha.gui;

import com.alpha.Entite.Hotel;
import com.alpha.Entite.Stadium;
import com.codename1.components.ToastBar;
import com.codename1.googlemaps.MapContainer;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.maps.Coord;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.SideMenuBar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.util.Callback;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Sof
 */
public class GoogleMapsTestApp {
     private static final String HTML_API_KEY = "";
    private Form current;
  private static final String MAPS_KEY = "AIzaSyBJubS9yF-ripULQ-z-TPve7cUS4Xr-1AQ";
    public void init(Object context) {
        try {
            Resources theme = Resources.openLayered("/theme");
            UIManager.getInstance().setThemeProps(theme.getTheme(theme.getThemeResourceNames()[0]));
            Display.getInstance().setCommandBehavior(Display.COMMAND_BEHAVIOR_SIDE_NAVIGATION);
            UIManager.getInstance().getLookAndFeel().setMenuBarClass(SideMenuBar.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     public static Coord[] decode(final String encodedPath) {
        int len = encodedPath.length();
        final ArrayList<Coord> path = new ArrayList<Coord>();
        int index = 0;
        int lat = 0;
        int lng = 0;

        while (index < len) {
            int result = 1;
            int shift = 0;
            int b;
            do {
                b = encodedPath.charAt(index++) - 63 - 1;
                result += b << shift;
                shift += 5;
            } while (b >= 0x1f);
            lat += (result & 1) != 0 ? ~(result >> 1) : (result >> 1);

            result = 1;
            shift = 0;
            do {
                b = encodedPath.charAt(index++) - 63 - 1;
                result += b << shift;
                shift += 5;
            } while (b >= 0x1f);
            lng += (result & 1) != 0 ? ~(result >> 1) : (result >> 1);

            path.add(new Coord(lat * 1e-5, lng * 1e-5));
        }
        Coord[] p = new Coord[path.size()];
        for (int i = 0; i < path.size(); i++) {
            p[i] = path.get(i);
        }

        return p;
    }
     public static String getRoutesEncoded(Coord src, Coord dest) {
        String ret = "";
        try {
            ConnectionRequest request = new ConnectionRequest("https://maps.googleapis.com/maps/api/directions/json", false);
            request.addArgument("key", MAPS_KEY);
            request.addArgument("origin", src.getLatitude() + "," + src.getLongitude());
            request.addArgument("destination", dest.getLatitude() + "," + dest.getLongitude());

            NetworkManager.getInstance().addToQueueAndWait(request);
            Map<String, Object> response = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8"));
            if (response.get("routes") != null) {
                ArrayList routes = (ArrayList) response.get("routes");
                if (routes.size() > 0)
                    ret = ((LinkedHashMap) ((LinkedHashMap) ((ArrayList) response.get("routes")).get(0)).get("overview_polyline")).get("points").toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }
    public static void getRoutesEncodedAsync(Coord src, Coord dest, Callback callback) {
        ConnectionRequest request = new ConnectionRequest("https://maps.googleapis.com/maps/api/directions/json", false) {
            @Override
            protected void readResponse(InputStream input) throws IOException {
                String ret = "";
                Map<String, Object> response = new JSONParser().parseJSON(new InputStreamReader(input, "UTF-8"));
                if (response.get("routes") != null) {
                    ArrayList routes = (ArrayList) response.get("routes");
                    if (routes.size() > 0)
                        ret = ((LinkedHashMap) ((LinkedHashMap) ((ArrayList) response.get("routes")).get(0)).get("overview_polyline")).get("points").toString();
                }
                callback.onSucess(ret);
            }


        };
        request.addArgument("key", MAPS_KEY);
        request.addArgument("origin", src.getLatitude() + "," + src.getLongitude());
        request.addArgument("destination", dest.getLatitude() + "," + dest.getLongitude());

        NetworkManager.getInstance().addToQueue(request);
    }
  public static Coord getCoords(String address) {
        Coord ret = null;
        try {
            ConnectionRequest request = new ConnectionRequest("https://maps.googleapis.com/maps/api/geocode/json", false);
            request.addArgument("key", HTML_API_KEY);
            request.addArgument("address", address);

            NetworkManager.getInstance().addToQueueAndWait(request);
            Map<String, Object> response = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8"));
            if (response.get("results") != null) {
                ArrayList results = (ArrayList) response.get("results");
                if (results.size() > 0) {
                    LinkedHashMap location = (LinkedHashMap) ((LinkedHashMap) ((LinkedHashMap) results.get(0)).get("geometry")).get("location");
                    ret = new Coord((double) location.get("lat"), (double) location.get("lng"));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }
    public void start(double geolat , double geolong , Resources resss , Stadium st ) {
        if (current != null) {
            current.show();
            return;
        }
        Form hi = new Form("Stadium's position : ");
        hi.setLayout(new BorderLayout());
        final MapContainer cnt = new MapContainer(HTML_API_KEY);
        cnt.setMapType(2);
        cnt.setCameraPosition(new Coord(geolat, geolong));
         Coord src = new Coord(31.2001, 29.9187);
        Coord dest = new Coord(30.0444, 31.2357);
        // get the routes using google directions api
        String encoded = getRoutesEncoded(src, dest);
        // decode the routes in an arry of coords
        Coord[] coords = decode(encoded);

        cnt.addPath(coords);
         Style s = new Style();
         s.setBgTransparency(0);
        s.setFgColor(0x007700);
        cnt.addMarker(FontImage.createMaterial(FontImage.MATERIAL_LOCATION_ON, s).toEncodedImage(), src, "", "", null);
        cnt.addMarker(FontImage.createMaterial(FontImage.MATERIAL_LOCATION_ON, s).toEncodedImage(), dest, "", "", null);
        
        FontImage markerImg = FontImage.createMaterial(FontImage.MATERIAL_LOCATION_ON, s, Display.getInstance().convertToPixels(3));
       
         cnt.addMarker(
                    EncodedImage.createFromImage(markerImg, false),
                    cnt.getCameraPosition(),
                    "Hi marker",
                    "Optional long description",
                     evt -> {
                             ToastBar.showMessage("You clicked the marker", FontImage.MATERIAL_PLACE);
                     }
            );
        
        Button btnMoveCamera = new Button("Back");
        btnMoveCamera.addActionListener(e->{
             new AfficherSingleStade(resss,st).show();
        });
         Button btnAddMarker = new Button("Show Marker");
        btnAddMarker.addActionListener(e->{
                        cnt.setMapType(2);
                        cnt.zoom(new Coord(geolat, geolong), 16);
            cnt.setCameraPosition(new Coord(geolat, geolong));
            cnt.addMarker(
                    EncodedImage.createFromImage(markerImg, false),
                    cnt.getCameraPosition(),
                    st.getName(),
                    st.getCity(),
                     evt -> {
                             ToastBar.showMessage("It's Here ! ", FontImage.MATERIAL_PLACE);
                     }
            );

        });

        Container root = LayeredLayout.encloseIn(
                BorderLayout.center(cnt),
                BorderLayout.south(
                        FlowLayout.encloseBottom(btnMoveCamera, btnAddMarker)
                )
        );

        hi.add(BorderLayout.CENTER, root);
        hi.show();

    }
     public void start(double geolat , double geolong , Resources resss , Hotel h ) {
        if (current != null) {
            current.show();
            return;
        }
        Form hi = new Form("Hotel's position : ");
        hi.setLayout(new BorderLayout());
        final MapContainer cnt = new MapContainer(HTML_API_KEY);
        cnt.setMapType(2);
        cnt.setCameraPosition(new Coord(geolat, geolong));
         Coord src = new Coord(31.2001, 29.9187);
        Coord dest = new Coord(30.0444, 31.2357);
        // get the routes using google directions api
        String encoded = getRoutesEncoded(src, dest);
        // decode the routes in an arry of coords
        Coord[] coords = decode(encoded);

        cnt.addPath(coords);
         Style s = new Style();
         s.setBgTransparency(0);
        s.setFgColor(0x007700);
        cnt.addMarker(FontImage.createMaterial(FontImage.MATERIAL_LOCATION_ON, s).toEncodedImage(), src, "", "", null);
        cnt.addMarker(FontImage.createMaterial(FontImage.MATERIAL_LOCATION_ON, s).toEncodedImage(), dest, "", "", null);
        
        FontImage markerImg = FontImage.createMaterial(FontImage.MATERIAL_LOCATION_ON, s, Display.getInstance().convertToPixels(3));
       
         cnt.addMarker(
                    EncodedImage.createFromImage(markerImg, false),
                    cnt.getCameraPosition(),
                    "Hi marker",
                    "Optional long description",
                     evt -> {
                             ToastBar.showMessage("You clicked the marker", FontImage.MATERIAL_PLACE);
                     }
            );
        
        Button btnMoveCamera = new Button("Back");
        btnMoveCamera.addActionListener(e->{
             new AfficherSingleHotel(resss,h).show();
        });
         Button btnAddMarker = new Button("Show Marker");
        btnAddMarker.addActionListener(e->{
                        cnt.setMapType(2);
                        cnt.zoom(new Coord(geolat, geolong), 16);
            cnt.setCameraPosition(new Coord(geolat, geolong));
            cnt.addMarker(
                    EncodedImage.createFromImage(markerImg, false),
                    cnt.getCameraPosition(),
                    h.getNom(),
                    h.getCity(),
                     evt -> {
                             ToastBar.showMessage("It's Here ! ", FontImage.MATERIAL_PLACE);
                     }
            );

        });

        Container root = LayeredLayout.encloseIn(
                BorderLayout.center(cnt),
                BorderLayout.south(
                        FlowLayout.encloseBottom(btnMoveCamera, btnAddMarker)
                )
        );

        hi.add(BorderLayout.CENTER, root);
        hi.show();

    }

    public void stop() {
        current = Display.getInstance().getCurrent();
    }

    public void destroy() {
    }
}
