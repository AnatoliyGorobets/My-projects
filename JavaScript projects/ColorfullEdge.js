function setColor(pixel, Red, Green, Blue) {
    pixel.setRed(Red);
    pixel.setGreen(Green);
    pixel.setBlue(Blue);
    return(pixel);
}

function borderOnDifferentEdges(pixel, image, VerticalBorder, HorizontalBorder) {
 x = pixel.getX();
 y = pixel.getY();
 if (x < VerticalBorder || 
    (x >= image.getWidth() - VerticalBorder)) {  return true; }

 if (y < HorizontalBorder ||
    (y >= image.getHeight() - HorizontalBorder)) { return true; }
 return false;
}

var image = new SimpleImage("duke_blue_devil.png");
print(image);
for (var pixel of image.values()) {
    if (borderOnDifferentEdges(pixel, image, 25, 10)) {
        pixel = setColor(pixel, 0, 0, 0);
    }
}
print(image);
