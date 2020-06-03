<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/plantDetails.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"/>
</head>
  <body>
     <h3 class="text-center"><mark>Here is the all plant's details information</mark></h3>
        <div style="float: right;">
        <form action ="/" method = "GET">
            <input type="hidden" name="">
            <button type="submit" class="btn btn-primary">Go back to home</button>
        </form>
        </div>
     <table  class="table table-striped">
         <tr>
             <th>Id</th>
             <th>Genus</th>
             <th>Species</th>
             <th>Cultivar</th>
             <th>Common</th>
         </tr>
         <c:forEach items="${plants}" var="plant">
         <tr>
             <td>
                 <c:out value="${plant.id}" />
             </td>
             <td>
                 <c:out value="${plant.genus}" />
             </td>
             <td>
                 <c:out value="${plant.species}" />
             </td>
             <td>
                 <c:out value="${plant.cultivar}" />
             </td>
              <td>
                  <c:out value="${plant.common}" />
              </td>
         </tr>
         </c:forEach>
     </table>
  </body>
</html>