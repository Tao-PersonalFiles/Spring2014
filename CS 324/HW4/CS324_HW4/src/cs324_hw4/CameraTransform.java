/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package cs324_hw4;

/**
 *
 * @author 张涛
 */
public final class CameraTransform {
        
    Matrix m = new Matrix();
        // set the final CAMERA transform matrix
    public double [][] CAMERA;
    
        // enum transform code
    public enum tfCode{
        X_TRANS, Y_TRANS, Z_TRANS,
        X_ROT, Y_ROT, Z_ROT,
        PERSPECTIVE,
        SCALE,
        ORIGIN
    }
    
    public CameraTransform(){
        CAMERA = m.makeMatrix(4, 4);
    }
        // init
    public CameraTransform(
            double fX, double fY, double fZ,
            double theta, double phi, double alpha,
            double r)
    {
        CAMERA = m.makeMatrix(4, 4);
        DefineCameraTransform(fX, fY, fZ, theta, phi, alpha, r);
    }
    
    public void getCameraTransform(double [][] cM){
        m.copyMatrix(CAMERA, cM);
    }
    
    public void setCameraTransform(double [][] cT){
        m.copyMatrix(cT, CAMERA);
    }
    
    /* 
     *
     */
    public void DefineCameraTransform(
            double fX, double fY, double fZ,
            double theta, double phi, double alpha,
            double r)
    {
            // Apply translation transformations
        DefineElementaryTransform(CAMERA, tfCode.X_TRANS, -fX);
        BuildElementaryTransform(CAMERA, tfCode.Y_TRANS, -fY);
        BuildElementaryTransform(CAMERA, tfCode.Z_TRANS, -fZ);
            // Aplly rotation transformation
        BuildElementaryTransform(CAMERA, tfCode.Y_ROT, -theta);
        BuildElementaryTransform(CAMERA, tfCode.X_ROT, phi);
        BuildElementaryTransform(CAMERA, tfCode.Z_ROT, -alpha);
            // Apply perspective transformation
        BuildElementaryTransform(CAMERA, tfCode.PERSPECTIVE, r);
    }
    
    /* Defines (initializes) an elementary transformation matrix
     * where M: a 4*4 matrix
     * code: transform code type (translation, rotation, scaling, perspective)
     * value: the magnitude of the transformation
     */
    void DefineElementaryTransform(
            double [][] M,
            tfCode code,
            double tfValue)
    {
            // change matrix value based on tfCode
        switch(code){
            case X_TRANS:   // x translation
                M[0][0] = 1;
                M[1][1] = 1;
                M[2][2] = 1;
                M[3][3] = 1;
                M[3][0] = tfValue;
                break;
            case Y_TRANS:   // y translation
                M[0][0] = 1;
                M[1][1] = 1;
                M[2][2] = 1;
                M[3][3] = 1;
                M[3][1] = tfValue;
                break;
            case Z_TRANS:   // z translation
                M[0][0] = 1;
                M[1][1] = 1;
                M[2][2] = 1;
                M[3][3] = 1;
                M[3][2] = tfValue;
                break;
            case X_ROT:     // x rotation
                M[0][0] = 1;
                M[3][3] = 1;
                M[1][1] = Math.cos(tfValue*2*Math.PI/360);
                M[2][2] = Math.cos(tfValue*2*Math.PI/360);
                M[1][2] = Math.sin(tfValue*2*Math.PI/360);
                M[2][1] = -Math.sin(tfValue*2*Math.PI/360);
                break;
            case Y_ROT:     // y rotation
                M[1][1] = 1;
                M[3][3] = 1;
                M[0][0] = Math.cos(tfValue*2*Math.PI/360);
                M[2][2] = Math.cos(tfValue*2*Math.PI/360);
                M[0][2] = -Math.sin(tfValue*2*Math.PI/360);
                M[2][0] = Math.sin(tfValue*2*Math.PI/360);
                break;
            case Z_ROT:     // z rotation
                M[2][2] = 1;
                M[3][3] = 1;
                M[0][0] = Math.cos(tfValue*2*Math.PI/360);
                M[1][1] = Math.cos(tfValue*2*Math.PI/360);
                M[0][1] = Math.sin(tfValue*2*Math.PI/360);
                M[1][0] = -Math.sin(tfValue*2*Math.PI/360);
                break;
            case PERSPECTIVE:   // perspective transform
                M[0][0] = 1;
                M[1][1] = 1;
                M[2][2] = 1;
                M[3][3] = 1;
                M[2][3] = -1/tfValue;
                break;
            case SCALE:
                M[0][0] = tfValue;
                M[1][1] = tfValue;
                M[2][2] = tfValue;
                M[3][3] = 1;
                break;
            case ORIGIN:
                M[0][0] = 1;
                M[1][1] = 1;
                M[2][2] = 1;
                M[3][3] = 1;
                break;
            default:
                System.err.println("Error: unknown transform code");
        }
    }
    
   /* The BuildElementaryTransform routine creates a new transform 
    * by premultiplying a transform by a new transform
    * (as specified by the arguments to the routine
    */
    private void BuildElementaryTransform(
            double [][] tfM,
            tfCode code,
            double tfValue)
    {
            // temporary transform matrix
        double [][] M = m.makeMatrix(4, 4);
        double [][] M2 = m.makeMatrix(4, 4);
            // create new transformation matrix
        DefineElementaryTransform(M, code, tfValue);
            // concatenate transformation matrices
        m.MultiplyMatrix(tfM, M, M2);
            // return concatenated transformation matrix
        m.copyMatrix(M2, tfM);
    }
}
