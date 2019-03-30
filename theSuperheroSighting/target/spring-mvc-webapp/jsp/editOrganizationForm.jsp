<%-- 
    Document   : editOrganizationForm
    Created on : Mar 5, 2019, 8:23:06 AM
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
  
        <h3>Edit Organization</h3>
        
        
        <sf:form class="form-horizontal" role="form"  modelAttribute="organization"
              action="editOrganization" method="POST">
            <div class="form-group">
                <label for="add-organization-name" class="col-md-4 control-label">Organization Name:</label>
                <div class="col-md-8">
                  <sf:input type="text" class="form-control" path="orgName" placeholder="Organization Name"/>
                  <sf:errors path="orgName" cssclass="error"></sf:errors>
                </div>
            </div>
            
            <div class="form-group">
                <label for="add-street" class="col-md-4 control-label">Street:</label>
                <div class="col-md-8">
                    <sf:input type="text" class="form-control" path="orgStreet" placeholder="Street"/>
                    <sf:errors path="orgStreet" cssclass="error"></sf:errors>
                </div>
            </div>
            <div class="form-group">
                <label for="add-city" class="col-md-4 control-label">City:</label>
                <div class="col-md-8">
                    <sf:input type="text" class="form-control" path="orgCity" placeholder="City"/>
                    <sf:errors path="orgCity" cssclass="error"></sf:errors>
                </div>
            </div>
            <div class="form-group">
                <label for="add-state" class="col-md-4 control-label">State:</label>
                <div class="col-md-8">
                    <sf:input type="text" class="form-control" path="orgState" placeholder="State"/>
                    <sf:errors path="orgState" cssclass="error"></sf:errors>
                </div>
            </div>
            <div class="form-group">
                <label for="add-zip" class="col-md-4 control-label">Zip:</label>
                <div class="col-md-8">
                    <sf:input type="text" class="form-control" path="orgZipCode" placeholder="Zip"/>
                    <sf:errors path="orgZipCode" cssclass="error"></sf:errors>
                </div>
            </div>
            
            <div class="form-group">
                <label for="add-contact" class="col-md-4 control-label">Contact:</label>
                <div class="col-md-8">
                    <sf:input type="text" class="form-control" path="contact" placeholder="Contact"/>
                    <sf:errors path="contact" cssclass="error"></sf:errors>
                    <sf:hidden path="organisationId"/>
                </div>
            </div>
            
            
            <div class="form-group">
                <div class="col-md-offset-4 col-md-8">
                    <input type="submit" class="btn btn-default" value="Update Organization"/>
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