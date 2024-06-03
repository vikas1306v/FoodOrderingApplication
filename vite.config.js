import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vitejs.dev/config/
export default defineConfig({

  // server:{
  //   host: 'localhost',
  //   port: 4000,
  //   proxy: {
  //     '/api/v1': {
  //       target: 'http://localhost:3000',
  //       changeOrigin: true,
  //     },
  //   },
  // },
  
  plugins: [react()],
})
