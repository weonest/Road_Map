let userName =  prompt("who's there","");
if (userName == "Admin"){
  let passWord = prompt("what's your password", '');
    if (passWord == "TheMaster"){
      alert("환영합니다");
    }
    else if (passWord == ''|| passWord == null) {
      alert("취소되었습니다.");
    }
    else {
      alert("인증에 실패하셨습니다.");
    }
}
else {
  alert("I don't know you");
}
