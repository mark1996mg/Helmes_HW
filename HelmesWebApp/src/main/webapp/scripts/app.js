
window.onload = function() {
    //let sectors = getAllSectors();
    
    //postData(sectors, 'sector/post');
    getSectorsFromDB();
    getUserDataFromSession();
    
}


function saveData() {

    let name = $('#fullNameInput').val();
    let secotorsVals = $('#sectors').val();
    let terms = $('#termsInput');

    let nameIsValid = checkIfNameIsNotEmpty(name);
    let sectorIsValid = checkIfSectorIsSelected(secotorsVals);
    let termsAgreed = checkIfTermsAgreed(terms);

    if (nameIsValid && sectorIsValid && termsAgreed) {

        let selectedSectors = getSelectedSectors(secotorsVals);

        let userData = {
            fullName: name,
            sectors: selectedSectors,
            hasAgreedToTerms: termsAgreed
        }

        postData(userData, 'user/post');
        

    }

}

function getSelectedSectors(secotorsVals) {
    let selectedSectors = [];
    for (let i = 0; i < secotorsVals.length; i++) {
        let sectorName = $.trim($('#sectors option[value='+ secotorsVals[i] +']').text());

        let sector = {
            sectorId: secotorsVals[i],
            sectorName: sectorName
        }
        selectedSectors.push(sector);
    }
    return selectedSectors;
}

function displaySelectedSectors(sectors) {
    let sectorsString = "";
    let sectorVals = [];
    for (let i = 0; i < sectors.length; i++) {
        let sectorName = $.trim(sectors[i].sectorName);
        let sectorVal = sectors[i].sectorId;

        sectorsString += sectorName + '<br>';
        sectorVals.push(sectorVal);
        
    }
    $("#sectors").val(sectorVals);
    $('#sectorField').html(sectorsString);
}

function getUserDataFromSession() {
    var url = window.location + 'user' + '/getSession';
    $.ajax({
        type: 'GET',
        url: url,
        success: function(result) {
            if (result != '') {
                $('#nameField').text(result.fullName);
                $('#fullNameInput').val(result.fullName);

                $('#termsField').text(result.hasAgreedToTerms);
                $( "#termsInput" ).prop( "checked", result.hasAgreedToTerms );

                displaySelectedSectors(result.sectors);
                closeModal();
            }
        },
        error: function(result) {
            console.log('failure');
        }
        
    });
}

function getSectorsFromDB() {
    var url = window.location + 'sector' + '/getAll';
    $.ajax({
        type: 'GET',
        url: url,
        success: function(result) {
            for (let i = 0; i < result.length; i++) {
                let sectorVal = result[i].sectorId;
                let sectornName = result[i].sectorName;
                $('#sectors').append('<option value=' + sectorVal + '>' + sectornName + '</option>');
            }
        },
        error: function(result) {
            console.log('failure');
        }
        
    });
}

function postData(data, api) {
    var url = window.location + api;
    $.ajax({
        type: 'POST',
        url: url,
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        data: JSON.stringify(data),
        success: function() {
            if (api == 'user/post') {
                getUserDataFromSession();
            }
        },
        error: function(result) {
            console.log('failure');
        }
        
    });
}


function getAllSectors() {
    let sectors = [];
    $('#sectors option').each(function(i) {
        let sectorVal = $(this).val();
        let sectorName = $('#sectors option[value='+ sectorVal +']').text();
        let sector = {
            sectorId: sectorVal,
            sectorName: sectorName,
        }
        sectors.push(sector);
        
    });
    return sectors;
}


function checkIfNameIsNotEmpty(name) {
    if ($.trim(name) == '') {
        $('#nameErrorMsg').css('display', 'block');
        return false;
    }
    else {
        $('#nameErrorMsg').css('display', 'none');
        return true;
    }
}

function checkIfSectorIsSelected(sectors) {
    if (sectors.length == 0) {
        $('#sectorErrorMsg').css('display', 'block');
        return false;
    }
    else {
        $('#sectorErrorMsg').css('display', 'none');
        return true;
    }
}

function checkIfTermsAgreed(terms) {
    if (terms.prop('checked')) {
        $('#termsErrorMsg').css('display', 'none');
        return true;
    }
    else {
        $('#termsErrorMsg').css('display', 'block');
        return false;
    }
}

function openModal() {
    $('.modal-window').css('display', 'block');
}

function closeModal() {
    $('.modal-window').css('display', 'none');
}

function closeModalAndResetValues() {
    closeModal();
    resetChangedValues();
}

function resetChangedValues() {
    $('.error-message').css('display', 'none');
    getUserDataFromSession();
}



