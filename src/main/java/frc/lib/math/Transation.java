/*
Translation + rotation = Transation 
This is the dumbest word ever. idiot

Holds both a rotation and translation
*/

package frc.lib.math;

public class Transation{

    private Translation2D kTranslation;
    private Rotation2D kRotation;

    /**
    * Constructor for Transation class
    *    
    * @param translation
    * The translation of the robot
    * 
    * @param rotation
    * The rotation of the robot
    */
    public Transation(Translation2D translation, Rotation2D rotation){
        kTranslation = translation;
        kRotation = rotation;
    }

    public void translate(Translation2D translation){
        kTranslation.translateBy(translation);
    }

    public void rotation(Rotation2D rotation){
        kRotation.rotateBy(rotation);
    }

    public void transate(Transation transation){
        kTranslation.translateBy(transation.getTranslation());
        kRotation.rotateBy(transation.getRotation());
    }

    public Translation2D getTranslation(){
        return kTranslation;
    }

    public Rotation2D getRotation(){
        return kRotation;
    }
}