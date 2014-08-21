<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script type='text/javascript' src='/js/raphael/prototype.js'></script>
<script type='text/javascript' src='/js/raphael/raphael.js'></script>
<script type='text/javascript' src='/js/raphael/analytics.js'></script>

<script type='text/javascript'>
   Element.observe(window,'load', function(){
      var w = 840; // you can make this dynamic so it fits as you would like
      var linechart = Raphael('line-chart', w, 250); // init the raphael obj and give it a width plus height
      drawLine({ // call the drawLine function
         holder: linechart, // pass through the raphael obj
         data_holder: 'd1', // find the table data source by id
         mastercolor: '#01A8F0', // set the line color
         spewidth: w, // pass in the same width
         showarea: true, // show the area
         mousecoords: 'rect' // rect (uses blanket mode) | circle (pinpoints the points)
      });
   });

</script>
</head>
<body>
<div id='line-chart'></div>
<table id="d1" style='display: none;'>
   <tfoot>
      <tr>
         <th>3/02</th>
         <th>3/03</th>
         <th>3/09</th>
         <th>3/16</th>
      </tr>
   </tfoot>
<tbody class='data'>
   <tr>
      <td>70</td>
      <td>70</td>
      <td>210</td>
      <td>490</td>
   </tr>
</tbody>
<tbody class='line1'>
   <tr>
      <td>70 Views</td>
      <td>70 Views</td>
      <td>210 Views</td>
      <td>490 Views</td>
   </tr>
</tbody>
<tbody class='line2'>
   <tr>
      <td>Mar 2nd 2011</td>
      <td>Mar 3rd 2011</td>
      <td>Mar 9th 2011</td>
      <td>Mar 16th 2011</td>
   </tr>
</tbody>
</table>
</body>
</html>