// Inspired by https://dribbble.com/shots/1821178-Sales-Report/

var data1 = {
  labels : ["Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday","test"],
  datasets : [
    {
      fillColor : "rgba(56,175,91,.1)",
      strokeColor : "rgba(56,175,91,1)",
      pointColor : "rgba(56,175,91,1)",
      pointStrokeColor : "rgba(0,0,0,0.6)",
      data : [-7.2, -5, -2, 2.2,-0.3,5.5,4,5]
    },
    {
      fillColor : "rgba(234,142,57,.1)",
      strokeColor : "rgba(234,142,57,1)",
      pointColor : "rgba(234,142,57,1)",
      pointStrokeColor : "rgba(0,0,0,0.6)",
      data : [-7.2, -5, -2, 2.2,-0.3,5.5,4,5]
    },
    {
      fillColor : "rgba(236,72,127,.1)",
      strokeColor : "rgba(236,72,127,1)",
      pointColor : "rgba(236,72,127,1)",
      pointStrokeColor : "rgba(0,0,0,0.6)",
      data : [-7.2, -5, -2, 2.2,-0.3,5.5,4,5]
    }
  ]
}


var options1 = {
  scaleFontColor : "rgba(255,255,255,0.7)",
  scaleLineColor : "rgba(0,0,0,0)",
  scaleGridLineColor : "rgba(255,255,255,0.1)",
  scaleFontFamily: "Open Sans",
  scaleFontSize: 14,
  bezierCurve : true,
  scaleShowLabels: true,
  pointDotRadius: 6,
  animation: true,
  scaleShowGridLines: true,
  datasetFill: true,
  responsive: true
}

new Chart(c1.getContext("2d")).Line(data1,options1);



var data2 = [{
    value: 80,
    color: "rgba(236,72,127,1)",
    label: ""
  },
  {
    value: 20,
    color: "#3c4449",
    label: ""
  }];
             

var options2 = {
  animation: false,
  responsive: true,
  segmentShowStroke: false,
  percentageInnerCutout: 90
}

new Chart($("#c2").get(0).getContext("2d")).Doughnut(data2,options2);

var data2 = [{
    value: 64,
    color: "rgba(234,142,57,1)",
    label: ""
  },
  {
    value: 36,
    color: "#3c4449",
    label: ""
  }];
             

var options2 = {
  animation: false,
  responsive: true,
  segmentShowStroke: false,
  percentageInnerCutout: 90
}

new Chart($("#c3").get(0).getContext("2d")).Doughnut(data2,options2);

var data2 = [{
    value: 34,
    color: "rgba(56,175,91,1)",
    label: ""
  },
  {
    value: 66,
    color: "#3c4449",
    label: ""
  }];
             

var options2 = {
  animation: false,
  responsive: true,
  segmentShowStroke: false,
  percentageInnerCutout: 90
}

new Chart($("#c4").get(0).getContext("2d")).Doughnut(data2,options2);