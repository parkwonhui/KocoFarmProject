<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<body>
<p>Click the button to set the href value to https://www.w3schools.com.</p>
<button onclick="myFunction()">Take me to w3schools.com</button>
<script>
function myFunction() {
  alert('Are you ready?');
  location.href = "https://www.w3schools.com";
}
</script>

</body>
</html>