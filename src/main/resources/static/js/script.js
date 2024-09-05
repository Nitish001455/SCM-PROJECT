console.log("Script loaded");

let currentTheme=getTheme();
//initial -->

document.addEventListener('DOMContentLoaded',() => {
    changetheme();

})



//TODO:
function changetheme(){

    //set web page
    changePageTheme(currentTheme, "");

    //set the listnervto change theme button
    const changethemeButton=document.querySelector("#theme_change_button");
    
    changethemeButton.addEventListener('click', (event) => {
        let oldTheme = currentTheme;
        
        console.log("change theme button clicked");
        
        if (currentTheme == "dark") {
            currentTheme="light";
        }else{
            currentTheme="dark";
        }
        changePageTheme(currentTheme, oldTheme);
    });
}

//set theme to localstorage
function setTheme(theme) {
    localStorage.setItem("theme", theme);
    
}

//get theme to localstorage
function getTheme() {
    let theme=localStorage.getItem("theme")
   return theme ? theme : "light";
    
}

//change current page theme
function changePageTheme(theme,oldTheme) {
    setTheme(currentTheme);
    if(oldTheme){
    document.querySelector("html").classList.remove(oldTheme);
    }
    document.querySelector("html").classList.add(theme);
     
    //change the text button
    document.querySelector("#theme_change_button")
    .querySelector("span").textContent =
   theme == "light" ? "Dark" : "Light";
    
    
}