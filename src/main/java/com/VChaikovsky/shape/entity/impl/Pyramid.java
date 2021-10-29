package com.vchaikovsky.shape.entity.impl;

import com.vchaikovsky.shape.creator.impl.PointCreator;
import com.vchaikovsky.shape.entity.GeometryEntity;
import com.vchaikovsky.shape.event.PyramidEvent;
import com.vchaikovsky.shape.exception.ShapeException;
import com.vchaikovsky.shape.observer.Observable;
import com.vchaikovsky.shape.observer.Observer;
import com.vchaikovsky.shape.util.IdGenerator;
import com.vchaikovsky.shape.validator.impl.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

public class Pyramid implements GeometryEntity, Observable {
   static final Logger logger = LogManager.getLogger();
   private long id;
   private Point basesCenter;
   private Point peak;
   private int basesCornersNumber;
   private double circumcircleRadius;
   private Set<Observer> observers = new HashSet<>();
   private DataValidator validator = DataValidator.getInstance();

   {
         id = IdGenerator.generateId();
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

      this.basesCenter = PointCreator
              .getInstance()
              .createEntity(basesCenterX, basesCenterY, basesCenterZ);
      this.peak = PointCreator
              .getInstance()
              .createEntity(peakX, peakY, peakZ);
      this.basesCornersNumber = basesCornersNumber;
      this.circumcircleRadius = circumcircleRadius;
   }

   public long getId() {
      return id;
   }

   public Point getBasesCenter() {
      return basesCenter;
   }

   public void setBasesCenter(Point basesCenter) {
      this.basesCenter = basesCenter;
      notifyObservers();
   }

   public Point getPeak() {
      return peak;
   }

   public void setPeak(Point peak) {
      this.peak = peak;
      notifyObservers();
   }

   public int getBasesCornersNumber() {
      return basesCornersNumber;
   }

   public void setBasesCornersNumber(int basesCornersNumber) {
      if(validator.isValidCornersNumber(basesCornersNumber)){
         this.basesCornersNumber = basesCornersNumber;
      }
      notifyObservers();
   }

   public double getCircumcircleRadius() {
      return circumcircleRadius;
   }

   public void setCircumcircleRadius(double circumcircleRadius) {
      if(validator.isValidRadius(circumcircleRadius)){
         this.circumcircleRadius = circumcircleRadius;
      }
      notifyObservers();
   }

   @Override
   public void attach(Observer observer) {
      if(observer != null) {
         observers.add(observer);
      } else {
         logger.warn("The observer " + observer + " is null.");
      }
   }

   @Override
   public void detach(Observer observer) {
      if(observers.contains(observer)) {
         observers.remove(observer);
      } else {
         logger.warn("The observer " + observer + " was not found.");
      }
   }

   @Override
   public void notifyObservers() {
      PyramidEvent event = new PyramidEvent(this);

      if (!observers.isEmpty()) {
         observers.forEach(o -> {
            try {
               o.parameterChanged(event);
            } catch (ShapeException e) {
               logger.error("Exception from notifyObserver", e);
            }
         });
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
              pyramid.basesCenter != null ? basesCenter.equals(pyramid.basesCenter) : basesCenter == null &&
              pyramid.peak != null ? peak.equals(pyramid.peak) : peak == null);
   }

   @Override
   public int hashCode() {
      int first = 31;
      int result = 1;
      result = result * first * (int) id;
      result = result * first + (basesCenter != null ? basesCenter.hashCode() : 0);
      result = result * first + (peak != null ? peak.hashCode() : 0);
      result = result * first * basesCornersNumber;
      result = result * first + (int) circumcircleRadius;

      return result;
   }

   @Override
   public String toString() {
      return new StringBuilder("Pyramid{")
              .append("id=")
              .append(id)
              .append(", basesCenter=")
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