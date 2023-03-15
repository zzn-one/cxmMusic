//解码jwt
function jwt() {
    let token = localStorage.getItem("jwt").split(".")
    let payload = JSON.parse(decodeURIComponent(escape(window.atob(token[1].replace(/-/g, "+").replace(/_/g, "/")))))
    return payload
}

export default jwt