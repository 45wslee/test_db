export default{
    install(Vue){
        //날짜 비교 함수
        Vue.prototype.$dateValidation = function(prevDate, currentDate){
            if(prevDate > currentDate) {
                //console.log(prevDate,currentDate);
                return false;
            }
            else{
                return true;
            }
        }
    }
}