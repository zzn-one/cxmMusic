//解码jwt(token)
function token() {
    let token = localStorage.getItem("token").split(".")
    let payload = JSON.parse(decodeURIComponent(escape(window.atob(token[1].replace(/-/g, "+").replace(/_/g, "/")))))
    return payload
}

export default token