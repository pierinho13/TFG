<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${!isAjax}">
<!-- footer del deep linking -->
			<c:if test="${incluirContentDiv}">
			</div>
			</c:if>
			<!-- end: Content -->
		</div>
		<!--/row-->
	</div>
	<!--/container-->
	<div class="clearfix"></div>
	
	<!-- modal -->
	<div class="modal fade modalModulo" id="${modulo}Modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
	  <div class="modal-dialog modal-lg">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="modalLabel">...</h4>
	      </div>
	      <div class="modal-body" id="body_${modulo}Modal">
	        ...
	      </div>
	    </div>
	  </div>
	</div>
	<!-- fin modal -->
</body>
</html>
<!-- fin footer deeplinking -->
</c:if>
