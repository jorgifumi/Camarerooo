# Camarerooo
Practice Android Basics KeepCoding.io Startup Engineering Master Bootcamp II

## Características

La aplicación comienza con una lista de Mesas vacía y nos permite añadir las que queramos mediante un diálogo personalizado.
Una vez tengamos alguna mesa, podemos acceder pinchando sobre ella a los pedidos que tenga en ese momento. En cualquier momento podemos añadir más pedidos pulsando sobre el botón flotante, esto hará que se descargue en segundo plano un JSON con la carta desde mocky.io, lo procesa y muestra los platos disponibles.
Pinchando sobre un plato veremos la foto (que se descargará en ese momento), nombre, descripción y precio de este, y los alérgenos que pueda contener.
Además tendremos un campo de notas para poner alguna observación en el pedido. Para finalizar y volver al menú podremos añadir o cancelar.
Cuando volvamos a la lista de mesas también tenemos la opción de calcular el total de la cuenta pinchando sobre el botón de la izquierda.

## Cosas a mejorar

Me ha faltado tiempo para crear distintos modos de visualización para distintos tamaños de pantalla, para ello algunos activity los tendría que haber convertido a fragment, confiaba que me daría tiempo pero finalmente no fue así.
Los layouts de las celdas habría que mejorarlos para mostrar más información, e incluso pasar de ListView a ReciclerView.
La apariencia general deja bastante que desear.
