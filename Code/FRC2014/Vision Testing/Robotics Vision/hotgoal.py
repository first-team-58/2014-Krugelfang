import rr
import math

list = rr.GetArrayVariable("BOUNDING_COORDINATES")
if len(list) > 1:
        targetPixelHeight = 0
        targetSamples = 0

        imageWidth = rr.GetVariable("IMAGE_WIDTH")
        distance = rr.GetVariable("distance")
        leftx = list[0]
        leftxx = list[2]
        rightx = list[4]
        rightxx = list[6]

        distanceOffCenter = (leftx + leftxx + rightx + rightxx) / 4
        displacement = imageWidth - distanceOffCenter
        print displacement
	if displacement > 0
	leftgoal = 1
	rightgoal = 0
	else
	rightgoal = 1
	leftgoal = 0
			
rr.SetVariable("LeftGoal", leftgoal)
rr.SetVariable("RightGoal", rightgoal)

		

        


        
