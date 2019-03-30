<%-- 
    Document   : editSightingForm
    Created on : Mar 5, 2019, 11:25:56 AM
    Author     : sonia
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- Directive for Spring Form tag libraries -->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Superhero Sighting</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <h2>Superhero Sighting</h2>
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
   
        <h3>Edit Sighting</h3>
       
        
        <sf:form class="form-horizontal" role="form" 
                 modelAttribute="sighting"
                 action="editSighting" method="POST">
            
            <div class="form-group">
                <label for="edit-sighting-date" class="col-md-4 control-label">Sighting Date:</label>
                <div class="col-md-8">
                  <sf:input type="text" class="form-control" path="sightingDate"  placeholder="${sighting.sightingDate}"/>           
                         
                  <sf:errors path="sightingDate" cssclass="error"></sf:errors>
               
                </div>
            </div>
            
              
                <div class="form-group">
                    <label for="superheroId" class="col-md-4 control-label">Superhero's List:</label>
                    <div class="col-md-8">
                    <select class="form-control" name="superheroId" multiple="true">
                        <c:forEach var="superhero" items="${sighting.heros}">
                            <option value="${superhero.superheroId}" selected="selected">${superhero.name}</option>   
                        </c:forEach>
                        <c:forEach var="superhero" items="${heroList}">
                            <option value="${superhero.superheroId}">${superhero.name}</option>
                        </c:forEach>
                    </select>
                    </div>     
                </div>

               
               <div class="form-group">
                    <label for="locationId" class="col-md-4 control-label">Locations:</label>
                    <div class="col-md-8">
                    <select class="form-control"  name="locationId">
                        <option value="${sighting.location.locationId}" selected="selected">${sighting.location.locName}</option>
                        <c:forEach var="location" items="${locList}">
                            <option value="${location.locationId}">${location.locName}</option>
                        </c:forEach>
                    </select>
                     <sf:hidden path="sightingId"/>    
                    </div>    
                </div> 
            
            
            <div class="form-group">
                <div class="col-md-offset-4 col-md-8">
                    <input type="submit" class="btn btn-default" value="Update Sighting"/>
                </div>
            </div>
        </sf:form>

    </div> <!-- End col div -->

</div> <!-- End row div -->
     
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>