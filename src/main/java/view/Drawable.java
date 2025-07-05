package view;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import model.port.Port;

public interface Drawable {
     static Shape createRectangleAsPolygon() {
         //this method is used for ports and packets rectangle and not computers
         double width = Port.SIZE;
         double height = Port.SIZE;

         Polygon rectangle = new Polygon();

         double topLeftX = -width / 2;
         double topLeftY = 0;
         double topRightX = width / 2;
         double topRightY = 0;
         double bottomRightX = width / 2;
         double bottomRightY = height;
         double bottomLeftX = -width / 2;
         double bottomLeftY = height;


         rectangle.getPoints().addAll(
                 topLeftX, topLeftY,
                 topRightX, topRightY,
                 bottomRightX, bottomRightY,
                 bottomLeftX, bottomLeftY
         );
         rectangle.setFill(Color.rgb(255, 255, 0, 0.2));



         rectangle.setStroke(Color.GREEN);
         rectangle.setStrokeWidth(4);


         return rectangle;
     }

     static Shape createTriangleAsPolygon() {
         Polygon triangle = new Polygon();
         triangle.getPoints().addAll(
                 0.0, 0.0,    // Peak at (0,0)
                 10.0, 20.0,// Bottom right point (slanted)
                 -10.0, 20.0       // Bottom left point
         );

         triangle.setFill(Color.rgb(255, 255, 0, 0.2)); // semi-transparent yellow fill
         triangle.setStroke(Color.YELLOW);
         triangle.setStrokeWidth(4);
         return triangle;
     }

     Shape draw();


}
