$(function(){
	
	$('#userTable').DataTable( {
        dom: 'Bfrtip',
        buttons: [
        	{
                extend: 'print',
                exportOptions: {
                    columns: ':visible'
                }
            },
            {
            	extend: 'pdfHtml5',
                exportOptions: {
                    columns: ':visible'
                }
            },
            'colvis'
        ],
        select: true
    } );
	
	$('[data-toggle="tooltip"]').tooltip();
})