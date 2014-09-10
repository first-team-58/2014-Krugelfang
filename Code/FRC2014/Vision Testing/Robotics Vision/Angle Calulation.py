import rr
import math

list = rr.GetArrayVariable("BFR_COORDINATES")
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
        m = math.fabs(distanceOffCenter - (imageWidth / 2 ))
        print m

        theta = math.atan((m / (139.08)))
        print math.cos(theta)
        distanceToWall = (math.cos(theta)) * distance
        print distanceToWall
        rr.SetVariable("DistanceToWall", distanceToWall)


        
