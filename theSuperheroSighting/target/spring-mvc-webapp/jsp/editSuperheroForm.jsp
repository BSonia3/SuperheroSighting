<%-- 
    Document   : editSuperheroForm
    Created on : Mar 5, 2019, 11:26:51 AM
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
    
   
        <h3>Edit Superhero</h3>
       
        <sf:form class="form-horizontal" role="form"  modelAttribute="superhero"
                 action="editSuperhero" method="POST">
            <div class="form-group">
                <label for="add-superhero-name" class="col-md-4 control-label">Superhero Name:</label>
                <div class="col-md-8">
                  <sf:input type="text" class="form-control" path="name" placeholder="Superhero Name"/>
                  <sf:errors path="name" cssclass="error"></sf:errors>
               
                </div>
            </div>
            <div class="form-group">
                <label for="add-superhero-description" class="col-md-4 control-label">Superhero Description:</label>
                <div class="col-md-8">
                  <sf:input type="text" class="form-control" path="description" placeholder="Superhero Description"/>
                  <sf:errors path="description" cssclass="error"></sf:errors>
                   
                </div>
            </div>    
              
              
                <div class="form-group">
                    <label for="organisationId" class="col-md-4 control-label">Organizations:</label>
                    <div class="col-md-8">
                    <select class="form-control" name="organisationId" multiple="multiple">
                        <c:forEach var="organisation" items="${superhero.orgs}">
                            <option value="${organisation.organisationId}" selected="selected">${organisation.orgName}</option>   
                        </c:forEach>
                        <c:forEach var="organisation" items="${orgList}">
                            <option value="${organisation.organisationId}">${organisation.orgName}</option>
                        </c:forEach>
                    </select>
                    </div>     
                </div>

               
               <div class="form-group">
                    <label for="superpowerId" class="col-md-4 control-label">Superpowers:</label>
                    <div class="col-md-8">
                    <select class="form-control"  name="superpowerId">
                        <option value="${superhero.superpower.superpowerId}" selected="selected">${superhero.superpower.superpower}</option>
                        <c:forEach var="superpower" items="${powerList}">
                            <option value="${superpower.superpowerId}">${superpower.superpower}</option>
                        </c:forEach>
                    </select>
                     <sf:hidden path="superheroId"/>    
                    </div>    
                </div> 
            
            
            <div class="form-group">
                <div class="col-md-offset-4 col-md-8">
                    <input type="submit" class="btn btn-default" value="Update Superhero"/>
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