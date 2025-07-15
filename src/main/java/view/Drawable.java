package view;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import model.port.Port;

public interface Drawable {
     default Shape createRectangleShape() {
         //this method is used for ports and packets rectangle and not computers

         Polygon rectangle = new Polygon();




         rectangle.getPoints().addAll(
                 -10.0, -10.0,
                 10.0, -10.0,
                 10.0,  10.0,
                 -10.0,  10.0
         );
         rectangle.setFill(Color.rgb(255, 255, 0, 0.2));



         rectangle.setStroke(Color.GREEN);
         rectangle.setStrokeWidth(4);


         return rectangle;
     }

     default Shape createTriangleShape() {
         Polygon triangle = new Polygon();
         triangle.getPoints().addAll(
                 0.0, -10.0,  // Peak
                 10.0,  10.0,  // Bottom right
                 -10.0,  10.0    // Bottom left point
         );

         triangle.setFill(Color.rgb(255, 255, 0, 0.2)); // semi-transparent yellow fill
         triangle.setStroke(Color.YELLOW);
         triangle.setStrokeWidth(4);

         return triangle;
     }

     default  Polygon createMaticShape() {
         Polygon maticShape= new Polygon();
         // The coordinates are calculated to meet the 20x20 size constraint,
         // with x=0 as the horizontal center and y=0 as the top.
         // The points are listed in order to draw the outline.
         maticShape.getPoints().addAll(

                 -2.5, -10.0,  // P1
                 -10.0,  -5.0,  // P2
                 -7.5,  10.0,  // P3
                 0.0,   5.0,  // P4
                 7.5,  10.0,  // P5
                 10.0,  -5.0,  // P6
                 2.5, -10.0,  // P7
                 0.0,  -5.0
         );


         maticShape.setFill(Color.rgb(255, 255, 0, 0.2)); // semi-transparent yellow fill
         maticShape.setStroke(Color.GRAY);
         maticShape.setStrokeWidth(4);
         return maticShape;
     }

     static Polygon createVpnShape(){

         Polygon shield = new Polygon();

         // Add the coordinates for the 4 vertices of the 20x20 shield.
         shield.getPoints().addAll(
                 0.0, -10.0,  // Top point
                 10.0,   0.0,  // Right shoulder
                 0.0,  10.0,  // Bottom point
                 -10.0,   0.0    // Left shoulder (-width/2, height/2)
         );

         // --- Style the shield ---
         shield.setStrokeWidth(4);
         shield.setFill(Color.STEELBLUE);
         shield.setStroke(Color.BLACK);

         return  shield;


     }

      void draw();


}
