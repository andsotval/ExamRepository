<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>
	<acme:form-textbox code="employer.job.form.label.reference" path="reference" placeholder="EE01-JJ01"/>
	<acme:form-textbox code="employer.job.form.label.title" path="title"/>
	<acme:form-moment code="employer.job.form.label.deadline" path="deadline"/>
	<acme:form-money code="employer.job.form.label.salary" path="salary"/>
	<acme:form-url code="employer.job.form.label.moreInfo" path="moreInfo"/>
	<acme:form-textarea code="employer.job.form.label.description" path="description"/>
	<jstl:if test="${command != 'create'}">
		<acme:form-checkbox code="employer.job.form.label.finalmode" path="finalMode"/>
	</jstl:if>
	
	<acme:form-submit 
		test="${command == 'create'}"
		code="employer.job.form.button.create" 
		action="/employer/job/create"/>
	<acme:form-submit 
		test="${command == 'show' and finalMode == false}"
		code="employer.job.form.button.update" 
		action="/employer/job/update"/>
	<acme:form-submit 
		test="${command == 'update' and finalMode == false}"
		code="employer.job.form.button.update" 
		action="/employer/job/update"/>
	<acme:form-submit 
		test="${command != 'create'}"
		code="employer.job.form.button.delete" 
		action="/employer/job/delete"/>
	<acme:form-submit 
		test="${command != 'create' and finalMode == false}"
		method="get"
		code="employer.job.duty.form.button.create" 
		action="/employer/duty/create?job.id=${id}"/>
		
  	<acme:form-submit
  		method="get"
  		test="${command != 'create' and hasPublish}"
		code="employer.job.form.button.duty" 
		action="/employer/duty/list?id=${id}"/>
	<acme:form-submit test="${command != 'create' and hasPublish}"
		method="get"
		code="employer.job.form.button.auditrecord" 
		action="/employer/auditrecord/list-by-job?id=${id}"/>
  	<acme:form-return code="employer.job.form.button.return"/>
</acme:form>
