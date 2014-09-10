import SimpleOpenNI.*;

SimpleOpenNI context;

void setup(){

  context = new SimpleOpenNI(this);
  
  if(context.enableIR() == false){
    println("error finding kinect");
    exit();
    return;
  }
  size(context.irWidth(), context.irHeight());
}

void draw(){
  loadPixles();
  
  context.update();
  
  image(context.irImage(),context.depthWidth(),0);
}
