package com.VChaikovsky.shapes.entity.impl;

import com.VChaikovsky.shapes.entity.GeometryEntity;
import com.VChaikovsky.shapes.validator.impl.DataValidator;

public class Pyramid implements GeometryEntity {
   private long id;
   private Point basesCenter;
   private Point peak;
   private int basesCornersNumber;
   private double circumcircleRadius;
   private DataValidator validator= new DataValidator();

   public long getID() {
      return id;
   }

   public void setId(long id) {
      if(id == 0) {
         this.id = id;
      }
   }

   public Pyramid(Point basesCenter, Point peak, int basesCornersNumber, double circumcircleRadius) {
      this.basesCenter = basesCenter;
      this.peak = peak;
      this.basesCornersNumber = basesCornersNumber;
      this.circumcircleRadius = circumcircleRadius;
   }

   public Pyramid(double basesCenterX, double basesCenterY, double basesCenterZ,
                  double peakX, double peakY, double peakZ,
                  int basesCornersNumber, double circumcircleRadius) {

      this.basesCenter = new Point(basesCenterX, basesCenterY, basesCenterZ);
      this.peak = new Point(peakX, peakY, peakZ);
      this.basesCornersNumber = basesCornersNumber;
      this.circumcircleRadius = circumcircleRadius;
   }

   public Point getBasesCenter() {
      return basesCenter;
   }

   public void setBasesCenter(Point basesCenter) {
      this.basesCenter = basesCenter;
   }

   public Point getPeak() {
      return peak;
   }

   public void setPeak(Point peak) {
      this.peak = peak;
   }

   public int getBasesCornersNumber() {
      return basesCornersNumber;
   }

   public void setBasesCornersNumber(int basesCornersNumber) {
      if(validator.isValidCornersNumber(basesCornersNumber)){
         this.basesCornersNumber = basesCornersNumber;
      }
   }

   public double getCircumcircleRadius() {
      return circumcircleRadius;
   }

   public void setCircumcircleRadius(double circumcircleRadius) {
      if(validator.isValidRadius(circumcircleRadius)){
         this.circumcircleRadius = circumcircleRadius;
      }
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      }
      if (o == null || getClass() != o.getClass()) {
         return false;
      }
      Pyramid pyramid = (Pyramid) o;
      return (basesCornersNumber == pyramid.basesCornersNumber &&
              Double.compare(pyramid.circumcircleRadius, circumcircleRadius) == 0 &&
              this.basesCenter.equals(pyramid.basesCenter) &&
              this.peak.equals(pyramid.peak));
   }

   @Override
   public int hashCode() {
      int result = (int) (basesCenter.getX() * basesCenter.getY() + basesCenter.getZ());
      result = result +(int) (peak.getX() + peak.getY() * peak.getZ());
      result = result * basesCornersNumber;
      result = result + (int) circumcircleRadius;

      return result;
   }

   @Override
   public String toString() {
      return new StringBuilder("Pyramid{")
              .append("basesCenter=")
              .append(basesCenter)
              .append(", peak=")
              .append(peak)
              .append(", basesCornersNumber=")
              .append(basesCornersNumber)
              .append(", circumcircleRadius=")
              .append(circumcircleRadius)
              .append('}')
              .toString();
   }
}