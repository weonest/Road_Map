<template>
  <div class="main-map" ref="map">
  </div>
</template>

<script>
import OlLayerTile from 'ol/layer/Tile.js';
import OlView from 'ol/View.js';
import OlMap from 'ol/Map.js';
import OSM from 'ol/source/OSM';
import {fromLonLat} from 'ol/proj.js'

export default {
  name: 'MainMap',
  data() {
    return {
      olMap: undefined,
    }
  },
  mounted() {
    this.olMap = new OlMap({
      target: this.$refs.map,
      layers: [
          new OlLayerTile({
            source: new OSM()
          })
      ],
      view: new OlView({
        center: fromLonLat([127.0188684, 37.1549168]), // 경기도 성남
        zoom: 10
      })
    })
  }

}
</script>

<style scoped>
.main-map {
  width: 100%;
  height: 100%;
}

</style>