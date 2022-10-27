function validateform(){  
var name=document.myform.name.value;  
var password=document.myform.password.value;
var confirmpassword=document.myform.confirmpassword.value;
var mno=document.myform.mno.value; 
var email=document.myform.email.value;     
  
if (name==null || name==""){  
  alert("Name can't be blank");  
  return false;  
}else if(password.length<6){  
  alert("Password must be at least 6 characters long.");  
  return false;  
  }   
  else if(confirmpassword !== password){  
  alert(" Password doesn't match !");  
  return false;  
  }   
  else if(mno.length<9)
  {
    alert("ENTER MOBILE NUMBER OF 10 DIGITS");
    return false;
  }
  else if(email.indexof('@')<=0)
  {
    alert("INVALID POSITION OF @");
    return false;
  }

}  