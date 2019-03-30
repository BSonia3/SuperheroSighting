<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
    <head>
  <meta charset="UTF-8">
  
        <title>Superhero Sighting</title>
        <!-- Bootstrap core CSS -->
  <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
  
  <style>
    #map{
      height:400px;
      width:100%;
    }
  </style>        
    </head>
    <body>
        <div class="container">
              <h3>Superhero Sighting</h3>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                 
                  <li role="presentation"
                      class="active">
                        <a href="${pageContext.request.contextPath}/displayHomePage">
                            Home
                        </a>
                  </li>
                  <li role="presentation">
                      <a href="${pageContext.request.contextPath}/displaySuperheroPage">
                         Superhero
                      </a>
                  </li>
                  <li role="presentation">          
                      <a href="${pageContext.request.contextPath}/displaySuperpowerPage">
                         Superpower
                      </a>
                  </li>
                  <li role="presentation">
                      <a href="${pageContext.request.contextPath}/displayLocationPage">
                         Location
                      </a>
                  </li>
                  <li role="presentation">
                      <a href="${pageContext.request.contextPath}/displayOrganizationPage">
                         Organization
                      </a>
                  </li>
                  <li role="presentation">
                      <a href="${pageContext.request.contextPath}/displaySightingPage">
                         Sighting
                      </a>
                  </li>
 
       
                    
                </ul>    
            </div>
            
           <div>
               
            
               
                 <p>
                    Welcome to the Superhero Sighting web service, In this application the user can report 
                    any sighting regarding superhero/super villain, location, and time.The application has 
                    the menu navigation on the top to allow the user to navigate from a page to another page;
                    The user can add superpower , location, organization as well as a superhero Informations.
                 </p>  
            </div>
         <hr><br>
           <div class="row">
                                                                
        <div class="col-md-6">
        <h5>List of the latest 10 Sightings</h5>
       
        <table id="superheroTable" class="table table-hover">
            <tr>
                <th width="20%">Date</th>
                <th width="25%"> Superhero</th>
                <th width="25%"> Location</th>
                <th width="15%"></th>
                <th width="15%"></th>
            </tr>
         <tbody>
         <c:forEach var="sighting" items="${sightingList}">
             <tr>
             <td>
                 <a href="displaySightingDetails?sightingId=${sighting.sightingId}"> 
                     ${sighting.sightingDate} </a>
             </td>
             <td>
             <c:forEach var="superhero" items="${sighting.heros}">
              <a href="displaySuperheroDetails?superheroId=${superhero.superheroId}"> 
                      
              <c:out value="${superhero.name}"/></a> 
              <br>
                             
              </c:forEach>
              </td>
              <td>
                <a href="displayLocationDetails?locationId=${sighting.location.locationId}">   
                    <c:out value="${sighting.location.locName}"/> </a> 
              </td>
             
             </tr>
         </c:forEach>
         </tbody>
        </table>  
   
      </div>
    
       
      <div class="col-md-6">
      <h4>map</h4>
    <div id="map"> 
        
    </div>
   
    <script>
        
       
       function initMap(){
                  
      // Map options
      var options = {
        zoom:5,
        center:{lat:39.9526,lng:-75.1652}
      }

      // New map
      var map = new google.maps.Map(document.getElementById('map'), options);

      // Listen for click on map
      google.maps.event.addListener(map, 'click', function(event){
        // Add marker
        addMarker({coords:event.latLng});
      });
       
      <c:forEach var="sighting" items="${sightingList}">
                   
                    var longitude=<c:out value="${sighting.location.longitude}"/>;
                    var latitude=<c:out value="${sighting.location.latitude}" /> ;
           
                  
                    
         
      // Array of markers
      var markers = [
          
            {
                coords:{lat:<c:out value="${sighting.location.latitude}" />,lng:<c:out value="${sighting.location.longitude}"/>}
            } , 
       /* {
          coords:{lat:40.2253569,lng:-82.6881395},
           
        },
        
        {
          coords:{lat:39.9526,lng:-75.1652},
            content:'<h1>Philadelphia PA</h1>'
        },
        {
          coords:{lat:40.741895,lng:-73.989308},
            content:'<h1>new york </h1>'
        },
        {
          coords:{lat:42.8584,lng:-70.9300},
          content:'<h1>Amesbury MA</h1>'
        },
        {
          coords:{lat:42.7762,lng:-71.0773}
        }*/
      ];
      
      // Loop through markers
      for(var i = 0;i < markers.length;i++){
        // Add marker
        addMarker(markers[i]);
      }

      // Add Marker Function
      function addMarker(props){
        var marker = new google.maps.Marker({
          position:props.coords,
          map:map,
          //icon:props.iconImage
        });

        // Check for customicon
        if(props.iconImage){
          // Set icon image
          marker.setIcon(props.iconImage);
        }

        // Check content
        if(props.content){
          var infoWindow = new google.maps.InfoWindow({
            content:props.content
          });

          marker.addListener('click', function(){
            infoWindow.open(map, marker);
          });
        }
      }
      
      </c:forEach>  
    }
    </script>
    <script async defer
    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDe2E8l32QMKHbNa7Ko29mReKiijbR44lc
&callback=initMap">
    </script>
     
    
  
      </div> <!-- End row div -->              
                         
                         
                         
                         
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
        
    </body>
</html>