function CanvasLine(x1,y1,x2,y2) {
    this.x1 = x1;
	this.x2 = x2;
	this.y1 = y1;
	this.y2 = y2;
}

function CanvasName(x,y,name){
	this.x = x;
	this.y = y;
	this.name = name;
}

function CanvasStar(x,y,magnitude,name){
	this.x = x;
	this.y = y;
	this.magnitude = magnitude;
	this.name = name;
	this.printStar = function(){
		console.log(this.name + " : " + this.magnitude);
	};
}