// Null check
function gfn_isNull(str) {
    if (str == null) return true;
    if (str == "NaN") return true;
    if (new String(str).valueOf() == "undefined") return true;  
    var chkStr = new String(str);
    if (chkStr.valueOf() == "undefined" ) return true;
    if (chkStr == null) return true;    
    if (chkStr.toString().length == 0 ) return true;   
    return false; 
}
 
function ComSubmit(opt_formId) {
    this.formId = gfn_isNull(opt_formId) == true ? "commonForm" : opt_formId;
    this.url = "";
     
    if(this.formId == "commonForm"){
        $("#commonForm")[0].reset();
    }
     
    this.setUrl = function setUrl(url){
        this.url = url;
    };
     
    this.addParam = function addParam(key, value){
        $("#"+this.formId).append($("<input type='hidden' name='"+key+"' id='"+key+"' value='"+value+"' >"));
    };
     
    this.submit = function submit(){
    	var frm = $("#"+this.formId)[0];
    	frm.action = this.url;
        frm.method = "post";
        frm.submit();
    };
}

// join id check
function joinIdCheck(input) {
	var message = '';
	var length = input.length;
		if (input === '') {
			message = '<span style="color:red"> 아이디를 입력해주세요  <span class="glyphicon glyphicon-remove"> </span>';
		} else if (length < 6 || length > 12) {
			message = '<span style="color:red"> 6~12자 사이로 입력해주세요  <span class="glyphicon glyphicon-remove"> </span>';
		} else {
			message = '<span style="color:green"> 사용 가능한 아이디입니다  <span class="glyphicon glyphicon-ok"> </span>';
		} document.getElementById("joinIdCheck").innerHTML = message;
}

// join password check
function joinPasswordCheck(password2) {
	var message = '';
	var password1 = document.getElementById("password1").value;
		if (password2 != password1) {
			message = '<span style="color:red"> 비밀번호가 다릅니다 <span class="glyphicon glyphicon-remove"> </span>';
		} else {
			message = '<span style="color:green"> 비밀번호가 일치합니다 <span class="glyphicon glyphicon-ok"> </span>';
		} document.getElementById("passwordCheck").innerHTML = message;
}


