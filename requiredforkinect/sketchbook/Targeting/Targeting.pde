import SimpleOpenNI.*;
import blobDetection.*;

PImage img;

BlobDetection theBlobDetection;
SimpleOpenNI context;

void setup(){
  context = new SimpleOpenNI(this);
 
  if(context.enableIR() == false){
    println("error finding kinect");
    exit();
    return;
  }
 
  theBlobDetection = new BlobDetection(context.irWidth(), context.irHeight());
  theBlobDetection.setPosDiscrimination(false);
  theBlobDetection.setThreshold(.038f);

  img = new PImage(80,60);
 
  background(200,0,0);
  size(context.irWidth(), context.irHeight());
  cam = context.GetIRMap();
}

void draw(){
  context.update();
 
 
  image(context.irImage(),context.depthWidth(),0);
 
 
  img.copy(cam, 0, 0, cam.width, cam.height,
                0, 0, img.width, img.height);
        fastblur(img, 2);
        theBlobDetection.computeBlobs(img.pixels);
        drawBlobsAndEdges(true,true);
  //img.copy(context.irImage(), 0, 0, width, height, 0, 0, img.width, img.height);
  //theBlobDetection.computeBlobs(img.pixles);
  //drawBlobsAndEdges(true,true);
}

void drawBlobsAndEdges(boolean drawBlobs, boolean drawEdges)
{
    noFill();
    Blob b;
    EdgeVertex eA,eB;
    for (int n=0 ; n<theBlobDetection.getBlobNb() ; n++)
    {
        b=theBlobDetection.getBlob(n);
        if (b!=null)
        {
            // Edges
            if (drawEdges)
            {
                strokeWeight(3);
                stroke(0,255,0);
                for (int m=0;m<b.getEdgeNb();m++)
                {
                    eA = b.getEdgeVertexA(m);
                    eB = b.getEdgeVertexB(m);
                    if (eA !=null && eB !=null)
                        line(
                            eA.x*width, eA.y*height,
                            eB.x*width, eB.y*height
                            );
                }
            }

            // Blobs
            if (drawBlobs)
            {
                strokeWeight(1);
                stroke(255,0,0);
                rect(
                    b.xMin*width,b.yMin*height,
                    b.w*width,b.h*height
                    );
            }

        }

      }
}
