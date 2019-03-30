<%-- 
    Document   : superheroCreateForm
    Created on : Mar 5, 2019, 11:35:14 AM
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
                           
                                                   
        
    <div class="col-md-6">
        <h3>Add New Superhero</h3>
       
        <form class="form-horizontal" 
              role="form" method="POST" 
              action="createSuperhero">
            
            <div class="form-group">
                <label for="add-superhero-name"> Name:</label>
                <div>
                  <input type="text" class="form-control" name="name" placeholder="Superhero Name"/>
                </div>
            </div>
            
            <div class="form-group">
                <label for="add-description" >Description:</label>
                <div >
                    <input type="text" class="form-control" name="description" placeholder="Description"/>
                </div>
            </div>
            
            <div class="form-group">
                    <label for="organisationId">Organizations:</label>
                    <select class="form-control" name="organisationId" multiple="multiple">
                          <c:forEach var="organisation" items="${orgList}">
                            <option value="${organisation.organisationId}">${organisation.orgName}</option>
                        </c:forEach>
                    </select>
             </div>

            <div class="form-group">
                    <label for="superpowerId">Superpowers:</label>
                    <select class="form-control" name="superpowerId">
                        <c:forEach var="superpower" items="${powerList}">
                            <option value="${superpower.superpowerId}">${superpower.superpower}</option>
                        </c:forEach>
                    </select>
            </div>
            
            <div class="form-group">
                <div class="col-md-offset-4 col-md-8">
                    <input type="submit" class="btn btn-default" value="Create Superhero"/>
                </div>
            </div>
        </form>
     </div> <!-- End col div -->
      
     <div class="col-md-offset-2 col-md-4">
         <br>
         <h2>${errorMsg}</h2>
         <br>
         <h4> Upload Picture For your Superhero</h4>
            <form role="form" method="POST" 
                  action="addPicture" 
                  enctype="multipart/form-data">
                <div class="form-group">
                    <label for="displayTitle">Display Title:</label>
                    <input type="text" 
                           id="displayTitle" 
                           name="displayTitle"/>
                </div>
                <div class="form-group">
                    <label for="picture">Upload File:</label> 
                    <input type="file" 
                           id="picture" 
                           name="picture"/>
                </div>
                <input type="submit" value="Upload Picture"/>
            </form>
        </div>
    </div> <!-- End row div -->
     </div>           
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

    </body>
</html>