<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script>
var xhr = new XMLHttpRequest();
var url = "http://openapi.animal.go.kr/openapi/service/rest/abandonmentPublicSrvc/abandonmentPublic"; /*URL*/
var queryParams = '?' + encodeURIComponent('ServiceKey') + '='+'TZzGtB8BZdZ0VsTPgpNVa1IQMCBLU9%2FlEriT0S4AFcqcswb4YiOAqJiR7So%2BJMbWd5fB0P6%2B8JQsI7EpN4KKrg%3D%3D'; /*Service Key*/
queryParams += '&' + encodeURIComponent('bgnde') + '=' + encodeURIComponent('20180301'); /*유기날짜 (검색 시작일) (YYYYMMDD) */
queryParams += '&' + encodeURIComponent('endde') + '=' + encodeURIComponent('20190501'); /*유기날짜 (검색 종료일) (YYYYMMDD) */
queryParams += '&' + encodeURIComponent('upkind') + '=' + encodeURIComponent('417000'); /*축종코드 - 개 : 417000 - 고양이 : 422400 - 기타 : 429900 */
queryParams += '&' + encodeURIComponent('kind') + '=' + encodeURIComponent(''); /*품종코드 (품종 조회 OPEN API 참조) */
queryParams += '&' + encodeURIComponent('upr_cd') + '=' + encodeURIComponent(''); /*시도코드 (시도 조회 OPEN API 참조) */
queryParams += '&' + encodeURIComponent('org_cd') + '=' + encodeURIComponent(''); /*시군구코드 (시군구 조회 OPEN API 참조) */
queryParams += '&' + encodeURIComponent('care_reg_no') + '=' + encodeURIComponent(''); /*보호소번호 (보호소 조회 OPEN API 참조) */
queryParams += '&' + encodeURIComponent('state') + '=' + encodeURIComponent('notice'); /*상태 - 전체 : null(빈값) - 공고중 : notice - 보호중 : protect */
queryParams += '&' + encodeURIComponent('pageNo') + '=' + encodeURIComponent('1'); /*페이지 번호*/
queryParams += '&' + encodeURIComponent('numOfRows') + '=' + encodeURIComponent('10'); /*페이지당 보여줄 개수*/
queryParams += '&' + encodeURIComponent('neuter_yn') + '=' + encodeURIComponent('Y'); /*중성화여부*/
xhr.open('GET', url + queryParams);
console.log(url+queryParams);
xhr.onreadystatechange = function () {
    if (this.readyState == 4) {
        alert('Status: '+this.status+' Headers: '+JSON.stringify(this.getAllResponseHeaders())+' Body: '+this.responseText);
    }
};

xhr.send('');
</script>
</head>
<body>

</body>
</html>