
function aesEncrypt(plainText, key) {
    let iv = "0QPaGkPmPF7SafLn"
    let cipher = CryptoJS.AES.encrypt(plainText, CryptoJS.enc.Utf8.parse(key), {
        mode: CryptoJS.mode.CBC,
        iv: CryptoJS.enc.Utf8.parse(iv),
        padding: CryptoJS.pad.Pkcs7
    });
    return cipher.toString(CryptoJS.enc.Utf8);
}


function aesDecrypt(encryptedData, key) {
    let iv = "0QPaGkPmPF7SafLn"
    let cipher = CryptoJS.AES.decrypt(encryptedData, CryptoJS.enc.Utf8.parse(key), {
        mode: CryptoJS.mode.CBC,
        iv: CryptoJS.enc.Utf8.parse(iv),
        padding: CryptoJS.pad.Pkcs7
    });
    return cipher.toString(CryptoJS.enc.Utf8);
}

function rsaEncrypt(plainText, publicKeyStr) {
    const jsEncrypt = new JSEncrypt();
    jsEncrypt.setPublicKey(getPublicKey(publicKeyStr));
    return jsEncrypt.encrypt(plainText)
}

function rasDecrypt(encryptedData, privateKeyStr) {
    const jsEncrypt = new JSEncrypt();
    jsEncrypt.setPrivateKey(getPrivateKey(privateKeyStr));
    return jsEncrypt.decrypt(encryptedData);
}

function getPublicKey(publicKeyStr) {
    let publicKey = "-----BEGIN PUBLIC KEY-----";
    publicKey += publicKeyStr;
    publicKey += "-----END PUBLIC KEY-----";
    return publicKey;
}

function getPrivateKey(privateKeyStr) {
    let privateKey = "-----BEGIN RSA PRIVATE KEY-----";
    privateKey += publicKeyStr;
    privateKey += "-----END RSA PRIVATE KEY-----";
    return privateKey;
}