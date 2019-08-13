<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>


<!--
<div class="d-flex flex-column align-items-end col-sm-2 col-md-3 col-lg-4 col-xl-5" style="height:600px; position: absolute; bottom: 0; left: 0">
    <div class="toast" role="status" aria-live="polite" aria-atomic="true" data-delay="5000" data-autohide="false" style="height:300px">
-->

<div class="d-flex flex-column align-items-start col-sm-2 col-md-3 col-lg-4 col-xl-5" style="border: 1px solid #eee; position: absolute; bottom: 20px; left: 0;">

   <div class="toast" role="status" aria-live="polite" aria-atomic="true" data-delay="5000" data-autohide="false" style="width: 100%; height: 50%; font-size: 14px">
        <div class="toast-header">
            <strong class="text-primary mr-auto">
                <div id="toastHeader" style="font-size: 18px; color: #e95420"></div>
            </strong>
            <small class="text-muted"></small>
            <button type="button" class="ml-2 mb-2 close" data-dismiss="toast" aria-label="Close">
                <span aria-hidden="true" style="font-size: 25px">&times;</span>
            </button>
        </div>
        <div class="toast-body" style="font-weight: 600; height: 70px">
            <div id="toastMessage" style="font-size: 15px"></div>
        </div>
    </div>
</div>
