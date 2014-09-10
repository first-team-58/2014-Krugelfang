// - Super Fast Blur v1.1 by Mario Klingemann <http://incubator.quasimondo.com>
// - BlobDetection library

import processing.video.*;
import blobDetection.*;
import SimpleOpenNI.*;
import processing.net.*;

Server myServer;
BlobDetection theBlobDetection;
PImage img;
boolean newFrame=false;
SimpleOpenNI context;
int i;
Blob target;
int data = 0;
String dataformat = "%d %d %d\n\r";


// ==================================================
// setup()
// ==================================================
void setup()
{
        myServer = new Server(this, 2048);
        
        context = new SimpleOpenNI(this);
        
        if(context.enableIR() == false){
         println("kinect not  connected");
         exit();
         return; 
        }

        context.setMirror(false);	
        size(context.irWidth(), context.irHeight());
	//size(1280,768);
	
	// BlobDetection
	// img which will be sent to detection (a smaller copy of the cam frame);
	img = new PImage(1366,768); 
	theBlobDetection = new BlobDetection(img.width, img.height);
	theBlobDetection.setPosDiscrimination(false);
        theBlobDetection.setConstants(500,200,1);
        //theBlobDetection.computeTriangles();
        //println(context.irWidth());
	theBlobDetection.setThreshold(0.7f); // will detect bright areas whose luminosity > 0.2f;
}

// ==================================================
// draw()
// ==================================================
void draw()
{

                context.update();
		image(context.irImage(),0,0,width,height);
                theBlobDetection.activeCustomFilter(this);
		img.copy(context.irImage(), 0, 0, context.irWidth(), context.irHeight(), 
				0, 0, img.width, img.height);
		//fastblur(img, 2);

		theBlobDetection.computeBlobs(img.pixels);

		drawBlobsAndEdges(true,true);
                int y = -1;
                int x = -1;
                try {
                  y = int(target.y * width);
                  x = int(target.x * height);
                } catch (Exception e) {
                   println("no target");
                }

                myServer.write(String.format(dataformat, x, y, -1));
                text((y),10,30);
 
//i = i+1;

	//println(data);
        //println(target.x * width);

}

// ==================================================
// drawBlobsAndEdges()
// ==================================================
void drawBlobsAndEdges(boolean drawBlobs, boolean drawEdges)
{
   // if (b==null){
     //return; 
    //}
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

// ==================================================
// Super Fast Blur v1.1
// by Mario Klingemann 
// <http://incubator.quasimondo.com>
// ==================================================
void fastblur(PImage img,int radius)
{
 if (radius<1){
    return;
  }
  int w=img.width;
  int h=img.height;
  int wm=w-1;
  int hm=h-1;
  int wh=w*h;
  int div=radius+radius+1;
  int r[]=new int[wh];
  int g[]=new int[wh];
  int b[]=new int[wh];
  int rsum,gsum,bsum,x,y,i,p,p1,p2,yp,yi,yw;
  int vmin[] = new int[max(w,h)];
  int vmax[] = new int[max(w,h)];
  int[] pix=img.pixels;
  int dv[]=new int[256*div];
  for (i=0;i<256*div;i++){
    dv[i]=(i/div);
  }

  yw=yi=0;

  for (y=0;y<h;y++){
    rsum=gsum=bsum=0;
    for(i=-radius;i<=radius;i++){
      p=pix[yi+min(wm,max(i,0))];
      rsum+=(p & 0xff0000)>>16;
      gsum+=(p & 0x00ff00)>>8;
      bsum+= p & 0x0000ff;
    }
    for (x=0;x<w;x++){

      r[yi]=dv[rsum];
      g[yi]=dv[gsum];
      b[yi]=dv[bsum];

      if(y==0){
        vmin[x]=min(x+radius+1,wm);
        vmax[x]=max(x-radius,0);
      }
      p1=pix[yw+vmin[x]];
      p2=pix[yw+vmax[x]];

      rsum+=((p1 & 0xff0000)-(p2 & 0xff0000))>>16;
      gsum+=((p1 & 0x00ff00)-(p2 & 0x00ff00))>>8;
      bsum+= (p1 & 0x0000ff)-(p2 & 0x0000ff);
      yi++;
    }
    yw+=w;
  }

  for (x=0;x<w;x++){
    rsum=gsum=bsum=0;
    yp=-radius*w;
    for(i=-radius;i<=radius;i++){
      yi=max(0,yp)+x;
      rsum+=r[yi];
      gsum+=g[yi];
      bsum+=b[yi];
      yp+=w;
    }
    yi=x;
    for (y=0;y<h;y++){
      pix[yi]=0xff000000 | (dv[rsum]<<16) | (dv[gsum]<<8) | dv[bsum];
      if(x==0){
        vmin[y]=min(y+radius+1,hm)*w;
        vmax[y]=max(y-radius,0)*w;
      }
      p1=x+vmin[y];
      p2=x+vmax[y];

      rsum+=r[p1]-r[p2];
      gsum+=g[p1]-g[p2];
      bsum+=b[p1]-b[p2];

      yi+=w;
    }
  }

}

boolean newBlobDetectedEvent(Blob b)
{
  double w = (b.w * img.width);
  double h = (b.h * img.height);
  double x = (b.x * img.width);
  double y = (b.y * img.height);
  double a = ((b.h * img.width)/(b.w * img.height));

 try {
  if ((a>0 && a<100) && w> 45)
 {     
    //if((y <= height/2)){
      
      target = b;
     return true; 
    //}
}
  return false;
}  catch (Exception e){
 println("no target");
 return false;
}
}

