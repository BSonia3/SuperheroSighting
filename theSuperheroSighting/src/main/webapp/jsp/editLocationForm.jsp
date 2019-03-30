<%-- 
    Document   : editLocationForm
    Created on : Mar 5, 2019, 8:19:04 AM
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
            <h1>Superhero Sighting</h1>
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
    <!-- 
        Add a col to hold the summary table - have it take up half the row 
    -->
    
   
        <h3>Edit Location</h3>
        <sf:form class="form-horizontal" role="form"  modelAttribute="location"
              action="editLocation" method="POST">
            <div class="form-group">
                <label for="add-location-name" class="col-md-4 control-label">Location Name:</label>
                <div class="col-md-8">
                  <sf:input type="text" class="form-control" path="locName" placeholder="Location Name"/>
                  <sf:errors path="locName" cssclass="error"></sf:errors>
                </div>
            </div>
            <div class="form-group">
                <label for="add-description" class="col-md-4 control-label">Description:</label>
                <div class="col-md-8">
                  <sf:input type="text" class="form-control" path="locDescription" placeholder="Description"/>
                  <sf:errors path="locDescription" cssclass="error"></sf:errors>
                </div>
            </div>
            <div class="form-group">
                <label for="add-street" class="col-md-4 control-label">Street:</label>
                <div class="col-md-8">
                    <sf:input type="text" class="form-control" path="street" placeholder="Street"/>
                    <sf:errors path="street" cssclass="error"></sf:errors>
                </div>
            </div>
            <div class="form-group">
                <label for="add-city" class="col-md-4 control-label">City:</label>
                <div class="col-md-8">
                    <sf:input type="text" class="form-control" path="city" placeholder="City"/>
                    <sf:errors path="city" cssclass="error"></sf:errors>
                </div>
            </div>
            <div class="form-group">
                <label for="add-state" class="col-md-4 control-label">State:</label>
                <div class="col-md-8">
                    <sf:input type="text" class="form-control" path="state" placeholder="State"/>
                    <sf:errors path="state" cssclass="error"></sf:errors>
                </div>
            </div>
            <div class="form-group">
                <label for="add-zip" class="col-md-4 control-label">Zip:</label>
                <div class="col-md-8">
                    <sf:input type="text" class="form-control" path="zipCode" placeholder="Zip"/>
                    <sf:errors path="zipCode" cssclass="error"></sf:errors>
                </div>
            </div>
            
            <div class="form-group">
                <label for="add-Longitude" class="col-md-4 control-label">Longitude:</label>
                <div class="col-md-8">
                    <sf:input type="text" class="form-control" path="longitude" placeholder="Longitude"/>
                    <sf:errors path="longitude" cssclass="error"></sf:errors>
                </div>
            </div>
            <div class="form-group">
                <label for="add-latitude" class="col-md-4 control-label">Latitude:</label>
                <div class="col-md-8">
                    <sf:input type="text" class="form-control" path="latitude" placeholder="Latitude"/>
                    <sf:errors path="latitude" cssclass="error"></sf:errors>
                    <sf:hidden path="locationId"/>
                </div>
            </div>
            
            
            <div class="form-group">
                <div class="col-md-offset-4 col-md-8">
                    <input type="submit" class="btn btn-default" value="Update Location"/>
                </div>
            </div>
        </sf:form>

    </div> <!-- End col div -->

</div> <!-- End row div -->
        </div>
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>