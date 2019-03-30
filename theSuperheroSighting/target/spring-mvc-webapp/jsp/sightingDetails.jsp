<%-- 
    Document   : sightingDetails
    Created on : Mar 5, 2019, 11:33:35 AM
    Author     : sonia
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Superhero Sighting</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <h3>Superhero Sighting</h3>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"> 
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
    <div class="row">
        <h4>Sighting Details</h4>
        <br>
      <div class="table-responsive">
                <table class="table table-bordered table-hover table-striped">
                    <thead>
                        <tr>
                            <th>Date</th>
                            <th>Superhero</th>
                             <th>Description</th>
                            <th>Location</th>
                            <th>Description</th>
                            <th>Street</th>
                            <th>City</th>
                            <th>State</th>
                            <th>Zip Code</th>
                            <th>Longitude</th>
                            <th>Latitude</th>
                           
                           
                        </tr>                            
                    </thead>
                    <tbody>
                        <c:forEach var="superhero" items="${sighting.heros}">
                         <td><c:out value="${sighting.sightingDate}"/></td>
                         <td><c:out value="${superhero.name}"/> 
                         <td><c:out value="${superhero.description}"/> 
                         <td><c:out value="${sighting.location.locName}"/></td>
                         <td><c:out value="${sighting.location.locDescription}"/></td> 
                         <td><c:out value="${sighting.location.street}"/></td> 
                         <td><c:out value="${sighting.location.city}"/></td>
                         <td><c:out value="${sighting.location.state}"/></td> 
                         <td><c:out value="${sighting.location.zipCode}"/></td>
                         <td><c:out value="${sighting.location.longitude}"/></td> 
                         <td><c:out value="${sighting.location.latitude}"/></td> 
                        
                        
                             
                        </c:forEach>
                    </tbody>
                </table>
            <!-- picture album-->
      <div>
     <div>
       <c:forEach var="currentPicture" items="${heroPic}">
                <hr>
                ${currentPicture.title}<br>
                <img src="${pageContext.request.contextPath}/${currentPicture.filename}"
                     class="img-thumbnail" alt="${currentPicture.title}" width="100" height="100"><br> 
      </c:forEach>
      </div>
       
      </div> 
    </div> <!-- End col div -->
   

    </div> <!-- End col div -->

</div> <!-- End row div -->
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>