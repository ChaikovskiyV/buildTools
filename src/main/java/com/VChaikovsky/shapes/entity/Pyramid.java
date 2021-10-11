package com.VChaikovsky.shapes.entity;

import com.VChaikovsky.shapes.idgenerator.IdGenerator;

public class Pyramid {
   private static final long ID;
   private Point basesCenter;
   private Point peak;
   private int basesCornersNumber;
   private double circumcircleRadius;
   private double basesCenterX;
   private double basesCenterY;
   private double basesCenterZ;
   private double peakX;
   private double peakY;
   private double peakZ;

   static {
      ID = IdGenerator.generateId();
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
      this.basesCenterX = basesCenterX;
      this.basesCenterY = basesCenterY;
      this.basesCenterZ = basesCenterZ;
      this.peakX = peakX;
      this.peakY = peakY;
      this.peakZ = peakZ;
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
      if(basesCornersNumber >= 3){
         this.basesCornersNumber = basesCornersNumber;
      }
   }

   public double getCircumcircleRadius() {
      return circumcircleRadius;
   }

   public void setCircumcircleRadius(double circumcircleRadius) {
      if(circumcircleRadius > 0){
         this.circumcircleRadius = circumcircleRadius;
      }
   }

   public double getBasesCenterX() {
      return basesCenterX;
   }

   public void setBasesCenterX(double basesCenterX) {
      this.basesCenterX = basesCenterX;
   }

   public double getBasesCenterY() {
      return basesCenterY;
   }

   public void setBasesCenterY(double basesCenterY) {
      this.basesCenterY = basesCenterY;
   }

   public double getBasesCenterZ() {
      return basesCenterZ;
   }

   public void setBasesCenterZ(double basesCenterZ) {
      this.basesCenterZ = basesCenterZ;
   }

   public double getPeakX() {
      return peakX;
   }

   public void setPeakX(double peakX) {
      this.peakX = peakX;
   }

   public double getPeakY() {
      return peakY;
   }

   public void setPeakY(double peakY) {
      this.peakY = peakY;
   }

   public double getPeakZ() {
      return peakZ;
   }

   public void setPeakZ(double peakZ) {
      this.peakZ = peakZ;
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
      boolean result = (basesCornersNumber == pyramid.basesCornersNumber &&
              Double.compare(pyramid.circumcircleRadius, circumcircleRadius) == 0 &&
              this.basesCenter.equals(pyramid.basesCenter) &&
              this.peak.equals(pyramid.peak)) ||
      (basesCornersNumber == pyramid.basesCornersNumber &&
              Double.compare(pyramid.circumcircleRadius, circumcircleRadius) == 0 &&
              Double.compare(pyramid.basesCenterX, basesCenterX) == 0 &&
              Double.compare(pyramid.basesCenterY, basesCenterY) == 0 &&
              Double.compare(pyramid.basesCenterZ, basesCenterZ) == 0 &&
              Double.compare(pyramid.peakX, peakX) == 0 &&
              Double.compare(pyramid.peakY, peakY) == 0 &&
              Double.compare(pyramid.peakZ, peakZ) == 0);

      return result;
   }

   @Override
   public int hashCode() {
      return this.hashCode();
   }

   @Override
   public String toString() {
      String data = "";
      if(basesCenter != null && peak != null){
         data = "Pyramid{" +
                 "basesCenter=" + basesCenter +
                 ", peak=" + peak +
                 ", basesCornersNumber=" + basesCornersNumber +
                 ", circumcircleRadius=" + circumcircleRadius +
                 '}';
      } else {
         data = "Pyramid{" +
                 ", basesCenterX=" + basesCenterX +
                 ", basesCenterY=" + basesCenterY +
                 ", basesCenterZ=" + basesCenterZ +
                 ", peakX=" + peakX +
                 ", peakY=" + peakY +
                 ", peakZ=" + peakZ +
                 ", basesCornersNumber=" + basesCornersNumber +
                 ", circumcircleRadius=" + circumcircleRadius +
                 '}';
      }
      return data;
   }
}