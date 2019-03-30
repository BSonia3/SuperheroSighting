<%-- 
    Document   : sightingCreateForm
    Created on : Mar 5, 2019, 11:32:46 AM
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
            <h1>Superhero Sighting</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation"> 
                        <a href="${pageContext.request.contextPath}/displayHomePage">
                            Home
                        </a>
                  </li>
                  <li role="presentation"
                      class="active">
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
                             
                                                   
        
    <div class="col-md-8">
        <h3>Add New Sighting</h3>
        
        <form class="form-horizontal" 
              role="form" method="POST" 
              action="createSighting">
            
            
            <div class="form-group">
                    <label for="add-sighting-date">Date:</label>
                    <input name="sightingDate" type="LocalDate" class="form-control" placeHolder="yyyy-mm-dd" />                            
            </div>  
            
            
            <div class="form-group">
                    <label for="superheroId">Superhero List:</label>
                    <select class="form-control" name="superheroId" multiple="multiple">
                          <c:forEach var="superhero" items="${heroList}">
                            <option value="${superhero.superheroId}">${superhero.name}</option>
                        </c:forEach>
                    </select>
             </div>
            <div class="form-group">
                    <label for="locationId">Locations:</label>
                    <select class="form-control" name="locationId">
                        <c:forEach var="location" items="${locationList}">
                            <option value="${location.locationId}">${location.locName}</option>
                        </c:forEach>
                    </select>
            </div>
            
            <div class="form-group">
                <div class="col-md-offset-4 col-md-8">
                    <input type="submit" class="btn btn-default" value="Create Sighting"/>
                </div>
            </div>
        </form>
     </div> <!-- End col div -->

    </div> <!-- End row div -->
     </div>           
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>