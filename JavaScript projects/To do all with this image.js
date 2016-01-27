function parameters(x, y) {
	if (x > y) {
			var parameter = y;
		}	
		else  parameter = x;
	return(parameter);
}

function crop (picture, OurWidth, OurHeight) {
	var NewPic = new SimpleImage(OurWidth, OurHeight);
	var cropWidth = Math.floor((picture.getWidth() - OurWidth)/2)+1;
	var cropHeight = Math.floor((picture.getHeight() - OurHeight)/2)+1;
			for (var p of picture.values()) {
				var x = p.getX();
				var y = p.getY();
				if (x < (picture.getWidth() - cropWidth) && x > cropWidth) {
					if (y > cropHeight && y < (picture.getHeight()-cropHeight)) {
					var np = NewPic.getPixel(x-cropWidth, y-cropHeight);
					np.setRed(p.getRed());
					np.setGreen(p.getGreen());
					np.setBlue(p.getBlue());
					}

				}
			}	
			return (NewPic);
}
	
function chop2hide(image) {
	for (var pixel of image.values()) {
		pixel.setRed((Math.floor(pixel.getRed()/16))*16);
		pixel.setGreen((Math.floor(pixel.getGreen()/16))*16);
		pixel.setBlue((Math.floor(pixel.getBlue()/16))*16);
	}
	return(image);
}

function shift(image) {
	var NewImage  = new SimpleImage(image.getWidth(), image.getHeight());
	for (var pixel of image.values()) {
		var x = pixel.getX();
		var y = pixel.getY();
		var p2 = NewImage.getPixel(x, y);
		p2.setRed(Math.floor(pixel.getRed()/16));
		p2.setGreen(Math.floor(pixel.getGreen()/16));
		p2.setBlue(Math.floor(pixel.getBlue()/16));
	}
	return (NewImage);
}

function combine(image1, image2) {
	var stego = new SimpleImage(image1.getWidth(), image1.getHeight());	
	for (var p1 of image1.values()) {
		var x = p1.getX();
		var y = p1.getY();
		var p = stego.getPixel(x, y);
		var p2 = image2.getPixel(x, y);
		p.setRed(sum(p1.getRed(), p2.getRed()));
		p.setGreen(sum(p1.getGreen(), p2.getGreen()));
		p.setBlue(sum(p1.getBlue(), p2.getBlue()));
	}
	return(stego);
}

function sum(a, b) {
	var answer = a+b;
	if (answer > 255) print("Error answer is bigger than 255");
	return(answer);
}

function extract(image) {
	for (var p of image.values()) {
		p.setRed((p.getRed()%16)*16);
		p.setGreen((p.getGreen()%16)*16);
		p.setBlue((p.getBlue()%16)*16);
	}
	return(image);
}

function DoubleSize(image) {
		var DoubleImage = new SimpleImage((image.getWidth()*2),(image.getHeight()*2));
		for (var p of DoubleImage.values()) {
			var x = p.getX()/2;
			var y = p.getY()/2;
			var lp = image.getPixel(x, y);
			p.setRed(lp.getRed());
			p.setGreen(lp.getGreen());
			p.setBlue(lp.getBlue());
		}
		return(DoubleImage);
}

function Duplicate(image) {
		var DupleImage = new SimpleImage((image.getWidth()*2),(image.getHeight()*2));
			for (var p of DupleImage.values()) {
			var x = p.getX();
			var y = p.getY();
			var lp = image.getPixel(x%image.getWidth(), y%image.getHeight());
				p.setRed(lp.getRed());
				p.setGreen(lp.getGreen());
				p.setBlue(lp.getBlue());
			
			}
		return(DupleImage);
}

var FrontImage = new SimpleImage("gracestart.jpg");
var HideImage = new SimpleImage("gracehide.jpg");

	print(FrontImage);
	print("Width and Height of first picture");
	print(FrontImage.getWidth(), FrontImage.getHeight());

	print(HideImage);	
	print("Width and Height of the hide picture");
	print(HideImage.getWidth(), HideImage.getHeight());

var OurWidth = parameters(FrontImage.getWidth(), HideImage.getWidth());
var OurHeight = parameters(FrontImage.getHeight(), HideImage.getHeight());

FrontImage = crop(FrontImage, OurWidth, OurHeight);
HideImage = crop(HideImage, OurWidth, OurHeight);

	print(FrontImage, HideImage);
	print("Width and Height of croped pictures");
	print(FrontImage.getWidth(), FrontImage.getHeight());
	print(HideImage.getWidth(), HideImage.getHeight());

	print("Red at (42 42) in first picture");
var sPixel = FrontImage.getPixel(42,42);
	print(sPixel.getRed());
	print("Red at (42 42) in hide picture");
	sPixel = HideImage.getPixel(42,42);
	    print(sPixel.getRed());
FrontImage = chop2hide(FrontImage);
HideImage = shift(HideImage);

	print("Red at (42 42) in first picture after chop2hide");
var sPixel = FrontImage.getPixel(42,42);
    print(sPixel.getRed());
    print("Red at (42 42) in hide picture after shift");
    sPixel = HideImage.getPixel(42,42);
    print(sPixel.getRed());

var Stego = combine(FrontImage, HideImage);
	print(Stego);
	print("Red at (42 42) in stego");
	sPixel = Stego.getPixel(42,42);
    print(sPixel.getRed());

var ExImage = extract(Stego);
	print("This is hide image");
	print(ExImage);
	print("Red at (42 42) in hide image");
	sPixel = ExImage.getPixel(42,42);
    print(sPixel.getRed());

var double = DoubleSize(FrontImage);
print(double);
print(double.getWidth(), double.getHeight());

var duplic = Duplicate(FrontImage);
print(duplic);
print(duplic.getWidth(), duplic.getHeight());