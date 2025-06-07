pm.test("Status code is 200", function () {
    pm.response.to.have.status(200);
});

pm.test("Forecast contains multiple entries", function () {
    var jsonData = pm.response.json();
    pm.expect(jsonData.list.length).to.be.above(1);
});
